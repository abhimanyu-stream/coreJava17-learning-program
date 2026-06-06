package com.java17.interview.prepartion;

public class TemplateMethodPattern {
}
/**
 * Common flow fixed, subclass customizes steps.
 *
 * Example
 * abstract class ReportGenerator {
 *
 *     public final void generateReport() {
 *         fetchData();
 *         processData();
 *         export();
 *     }
 *
 *     abstract void fetchData();
 *
 *     void processData() {
 *         System.out.println("Common processing");
 *     }
 *
 *     abstract void export();
 * }
 *
 * Spring:
 *
 * JdbcTemplate
 * RestTemplate
 * KafkaTemplate
 *
 * Interview line:
 *
 * “Template defines skeleton; subclasses fill steps.”
 *
 */