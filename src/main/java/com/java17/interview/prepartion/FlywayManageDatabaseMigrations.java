package com.java17.interview.prepartion;

public class FlywayManageDatabaseMigrations {
}
/**
 * Using Spring Boot with Flyway to Manage Database Migrations
 * Managing changes to a database schema over time is one of the hardest parts of working on any backend system. Flyway solves this by versioning schema changes through SQL or Java-based migration scripts, and Spring Boot makes it easy to integrate Flyway directly into the application lifecycle. Today we will go through how Flyway works with Spring Boot behind the scenes, how to organize your migrations, how to version your changes safely, and how to make it all run on startup. You’ll also learn the differences between things like API key authentication and full user authorization, and how to keep your database migration process secure.
 * I publish free articles like this daily, if you want to support my work and get access to exclusive content and weekly recaps, consider subscribing to my Substack.
 * How Flyway Runs Migrations Automatically in Spring Boot
 * Flyway works directly with Spring Boot’s startup process without needing much input from you. After you include the right dependency, Spring Boot takes care of wiring it into the startup flow and running your migration scripts in the right order.
 * What Happens During Application Startup
 * Flyway hooks into Spring Boot early during the initialization process. The moment your application begins starting and the Spring context starts loading beans, Spring Boot detects Flyway through the presence of the flyway-core dependency in your classpath. That detection step is what triggers the Flyway auto-configuration.
 * Once detected, Spring Boot creates a FlywayAutoConfiguration bean and prepares the migration process before your application fully starts. Migrations run before any @PostConstruct methods, ApplicationRunner, or CommandLineRunner code is executed. This ordering is deliberate because Flyway needs to update the schema before anything tries to use the database.
 * By default, Flyway looks for migration scripts inside:
 * src/main/resources/db/migration
 * You can change this location using the following property in your application.properties or application.yml:
 * spring.flyway.locations=classpath:/custom-folder
 * Flyway applies scripts that follow the versioning pattern:
 * V1__init_schema.sql
 * V2__add_index_to_table.sql
 * These filenames are parsed by Flyway to determine the order. The prefix V is short for version, and double underscores separate the version from a human-readable description. Any other files in the migration folder that don’t match this format will be ignored.
 * Flyway doesn’t require version numbers to be consecutive, so skipping one doesn’t break anything by itself. But if you later add a script with a version lower than the most recent one applied, it’s treated as out-of-order and Flyway stops unless the outOfOrder setting is turned on. Changing a script that’s already been applied still causes a checksum mismatch, and Flyway exits with an error.
 * Transactional Behavior and Locking
 * Flyway runs migrations using transactions when the database supports it. On systems like PostgreSQL, this means all of your schema changes for a given migration will either complete or fail together. This gives a strong safety net, especially during upgrades. On databases that don’t support DDL transactions, like older MySQL versions, Flyway falls back to running commands outside a transaction but still tracks them in the metadata.
 * Here’s an example of how Flyway wraps your migrations inside a transaction block automatically (conceptually, not something you see in a script but I think it's a good visual):
 * BEGIN;
 * -- contents of V1__init_schema.sql
 * CREATE TABLE users (
 *     id SERIAL PRIMARY KEY,
 *     email VARCHAR(255) NOT NULL UNIQUE,
 *     created_at TIMESTAMP NOT NULL DEFAULT now()
 * );
 * COMMIT;
 * If that table creation fails, the transaction is rolled back and the migration is marked as failed in Flyway’s internal tracking table.
 * To prevent race conditions, Flyway takes a lock on the flyway_schema_history table using a SELECT ... FOR UPDATE (or your database’s equivalent). The lock lasts only for the duration of the migration transaction, there’s no extra row or column written to store the lock state. The lock is acquired before migration begins and released after all migrations have completed. If two instances of your application start at the same time, say in a scaled container environment, only one will get the lock. The other waits until the lock is free or throws an error, depending on configuration.
 * You can configure the number of retry attempts with this setting:
 * spring.flyway.lock-retry-count=10
 * This lets you tune how many times Flyway will retry acquiring the lock before giving up. It’s helpful when you’re dealing with clustered deployments or long-running migrations that could delay startup for other nodes.
 * If the lock can’t be obtained, Flyway fails fast. This prevents partial state or overlapping migrations. You should always check your logs if the application won’t start after a deployment. Flyway prints detailed logs about which migration failed, which one was running, and what SQL caused the issue.
 * Default Behavior Without Any Code
 * You don’t need to write a single line of Java to use Flyway with Spring Boot. Once the dependency is in place and your SQL files are organized properly, Flyway handles everything.
 * Here’s what the default behavior looks like with nothing more than the dependency and a few migration files:
 * <!-- pom.xml -->
 * <dependency>
 *     <groupId>org.flywaydb</groupId>
 *     <artifactId>flyway-core</artifactId>
 * </dependency>
 * Place your scripts in src/main/resources/db/migration, and name them like this:
 * V1__create_users.sql
 * V2__add_email_index.sql
 * That’s all. Spring Boot sees Flyway, initializes it during context loading, finds the migration scripts, and applies them in version order. Each script is applied exactly once. Flyway records the application in the flyway_schema_history table with a checksum, timestamp, and execution time. If you later change the contents of a file that’s already been run, Flyway detects the checksum mismatch and will throw an error on startup. That’s by design. Changing an applied migration file is considered a dangerous operation and not allowed without manual intervention.
 * You can check the applied migrations by querying the metadata table directly:
 * SELECT version, description, installed_on, success
 * FROM flyway_schema_history;
 * This gives you a full history of which migrations have run, when they were applied, and whether they succeeded. It’s helpful for debugging and for auditing schema changes in production.
 * You can also disable automatic migrations in some environments using:
 * spring.flyway.enabled=false
 * This is useful during tests or when migrations are handled externally. You can still trigger them manually by creating a Flyway bean and calling migrate() yourself if you need more control.
 * Get Alexander Obregon’s stories in your inbox
 * Join Medium for free to get updates from this writer.
 * Subscribe
 * Flyway’s default behavior works well out of the box and fits directly into Spring Boot’s startup lifecycle. The design is deliberate so that database changes always run before your code does anything else, reducing the chance that a table or column your app depends on is missing or misconfigured.
 * Writing and Managing Safe, Secure Migrations
 * Flyway handles the technical side of running migrations, but how you structure and manage your scripts determines whether they hold up well in real environments. Keeping the migration process safe means being careful with how scripts are named, how access is granted, and how changes are tracked. If you’re not careful with access or file structure, migrations can cause damage or leave your database in a state that’s hard to fix.
 * Directory Structure and File Naming
 * Flyway expects migration scripts to follow a very specific structure. This structure helps it understand the version history and figure out what needs to run. By default, Spring Boot tells Flyway to look in:
 * src/main/resources/db/migration
 * Files placed here should follow the pattern:
 * V1__create_users_table.sql
 * V2__add_email_index.sql
 * The V signals a versioned migration. The number tracks which scripts run first, and the double underscore separates the number from the description. The order matters. Flyway loads the files alphabetically and applies them based on the numeric version. If the numbers are out of sequence or skipped, Flyway stops and reports a validation error.
 * Each script should handle one logical change. That makes it easier to isolate problems and test new migrations. For example, instead of cramming both a table creation and an index into a single script, you can split them:
 * V3__create_orders_table.sql
 * V4__add_index_to_orders.sql
 * Avoid putting temporary notes or experiment files in the migration folder. If you’re trying things out, use a separate directory outside of db/migration so Flyway doesn’t attempt to parse or run them.
 * You can also use different folders for different modules if your project is split into parts. Just be sure to tell Flyway where to find them:
 * spring.flyway.locations=classpath:/db/main,classpath:/db/billing
 * That helps when you have multiple teams working on different parts of the schema, but it still keeps each file versioned and traceable.
 * Avoiding Mistakes With Repeatable Scripts
 * Flyway supports a second type of script called repeatable migrations. These are named with the prefix R__ and run every time the file content changes. You don’t give them a version number. A typical use would be refreshing views, reloading seed data, or updating stored procedures.
 * Example:
 * R__refresh_materialized_views.sql
 * Flyway tracks a checksum of the script content. If anything changes, it re-runs the script during startup. This is helpful when you need to make changes without bumping a version number, but it can also cause confusion if you’re not careful. If your repeatable script drops and recreates a view, that can break any code that runs between the drop and the recreate. For that reason, it’s a good idea to avoid repeatable scripts for things that directly impact application queries. Use them only for safe operations or in environments where you can tolerate short gaps.
 * Also, avoid mixing repeatable and versioned scripts for the same object. That tends to create conflicts or unexpected changes, especially when working across teams. If something started as a versioned change, keep evolving it with new versioned scripts.
 * Here’s a safe pattern for a view managed with a repeatable script:
 * -- R__update_sales_view.sql
 * CREATE OR REPLACE VIEW sales_summary AS
 * SELECT region, SUM(total_amount) AS total_sales
 * FROM orders
 * GROUP BY region;
 * The CREATE OR REPLACE clause helps avoid dropping the view and briefly making it unavailable. It replaces the existing definition in a single step.
 * Making Sure Scripts Run With the Right Access Level
 * Database migrations usually require elevated privileges. They often create tables, add indexes, modify constraints, and update seed data. Because of this, the account that runs migrations should not be the same account your application uses to talk to the database during normal operation.
 * Give Flyway its own database user. That user needs permission to run schema changes but shouldn’t be used by anything else. Here’s an example setup:
 * spring.flyway.url=jdbc:postgresql://db.internal.company.net:5432/customerdata
 * spring.flyway.user=flyway_migration_user
 * spring.flyway.password=${FLYWAY_PASSWORD}
 * Keep the password out of the source code. Use environment variables or secret management systems in production. Most cloud services and container platforms support secret storage natively, so there’s no reason to hardcode credentials or check them into version control.
 * Keep in mind that your application should have a different user configured for day-to-day queries. That user should not be able to drop tables or create new ones. This separation reduces the damage that can happen if a security issue ever exposes your application credentials.
 * If you use cloud-hosted databases, you can also take advantage of IAM-based access or rotate passwords regularly using automated tools.
 * Difference Between API Keys and Real User Authentication
 * API keys and user authentication do very different things. A lot of systems make the mistake of using API keys for everything, but that often ends up weakening your access controls.
 * An API key is a token meant to identify a system or a piece of software. These are typically used to authorize one service to talk to another. They work well for allowing access to internal endpoints, like monitoring tools or other microservices. User authentication, on the other hand, involves verifying who the user is and what they’re allowed to do. This usually involves a login process, password or token validation, and permission checks.
 * For database migrations, API keys should never be part of the equation. You shouldn’t expose migration controls through an HTTP interface at all, and you definitely shouldn’t allow remote triggers for migrations using an API key. If Flyway runs automatically on startup, that process stays local and internal, which is much safer. If you’re managing a migration dashboard or admin portal, you can use API keys for internal service access, but always back it up with real authentication before exposing controls. A properly scoped admin user should still go through a login screen and have real permissions attached to their account.
 * Flyway has no built-in support for API tokens or authentication because it’s meant to run inside the application lifecycle. The safest way to use it is to isolate it from public access and manage its database credentials with care.
 * Conclusion
 * Flyway works by reading and applying versioned migration scripts during Spring Boot’s startup, using the application context and configuration to decide what to run and when. It handles transactions, locking, and history tracking without needing extra code. The logic is clear and predictable, with automatic behavior that depends on file names, content checksums, and order. Security fits into the process through separation of access, not just in who runs what, but in how services identify themselves compared to how users authenticate. The mechanics behind Flyway make it easy to trust when used right, as long as the files are clean, access is kept separate, and migrations are treated as permanent changes to real infrastructure.
 * 1.	Flyway Migration Documentation
 * 2.	Spring Boot and Flyway
 * 3.	Spring Boot Auto-Configuration Docs
 * 4.	Spring Boot External Configuration Docs
 */