package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class  HourlyTaskScheduler{
    
    // 🔁 Every minute at 0 seconds (e.g., 12:00:00, 12:01:00, 12:02:00...)
    @Scheduled(cron = "0 * * * * *")
    public void runEveryMinute() {
        System.out.println("Running every minute at: " + LocalDateTime.now());
    }

    // 🔁 Every hour at 0 minutes and 0 seconds (e.g., 01:00:00, 02:00:00...)
    @Scheduled(cron = "0 0 * * * *")
    public void runHourlyTask() {
        System.out.println("Running hourly task at: " + LocalDateTime.now());
    }

    // 📅 Every day at 10:00:00 AM
    @Scheduled(cron = "0 0 10 * * *")
    public void runDailyAt10AM() {
        System.out.println("Running daily at 10:00 AM: " + LocalDateTime.now());
    }

    // 📅 Every day at 10:30:00 AM
    @Scheduled(cron = "0 30 10 * * *")
    public void runDailyAt1030AM() {
        System.out.println("Running daily at 10:30 AM: " + LocalDateTime.now());
    }

    // 🌙 Every day at midnight (00:00:00)
    @Scheduled(cron = "0 0 0 * * *")
    public void runDailyAtMidnight() {
        System.out.println("Running daily at midnight: " + LocalDateTime.now());
    }

    // 📆 6:00 AM on the first day of every month
    @Scheduled(cron = "0 0 6 1 * *")
    public void runMonthlyFirstDay() {
        System.out.println("Running on first day of month at 6:00 AM: " + LocalDateTime.now());
    }

    // 🗓 Every Monday at 9:00:00 AM
    @Scheduled(cron = "0 0 9 ? * MON")
    public void runEveryMonday() {
        System.out.println("Running every Monday at 9:00 AM: " + LocalDateTime.now());
    }

    // 🔔 Every day at 2:15:00 PM
    @Scheduled(cron = "0 15 14 * * *")
    public void runDailyAt215PM() {
        System.out.println("Running daily at 2:15 PM: " + LocalDateTime.now());
    }

    // 💼 Weekdays (Mon–Fri) at 12:00:00 PM
    @Scheduled(cron = "0 0 12 * * MON-FRI")
    public void runWeekdaysAtNoon() {
        System.out.println("Running weekdays at noon: " + LocalDateTime.now());
    }

    // 🎄 Every year on Dec 25th at midnight (Christmas)
    @Scheduled(cron = "0 0 0 25 12 *")
    public void runChristmas() {
        System.out.println("Merry Christmas! Running at: " + LocalDateTime.now());
    }

    // ⏲ Every 5 minutes (e.g., 10:00, 10:05, 10:10...)
    @Scheduled(cron = "0 0/5 * * * *")
    public void runEvery5Minutes() {
        System.out.println("Running every 5 minutes at: " + LocalDateTime.now());
    }

    // 🔄 Every 10 minutes (e.g., :00, :10, :20...)
    @Scheduled(cron = "0 */10 * * * *")
    public void runEvery10Minutes() {
        System.out.println("Running every 10 minutes at: " + LocalDateTime.now());
    }

    // 🔂 Every 15 seconds
    @Scheduled(cron = "*/15 * * * * *")
    public void runEvery15Seconds() {
        System.out.println("Running every 15 seconds at: " + LocalDateTime.now());
    }

    // 🌇 Every Sunday at 11:00:00 PM
    @Scheduled(cron = "0 0 23 ? * SUN")
    public void runEverySunday() {
        System.out.println("Running every Sunday at 11:00 PM: " + LocalDateTime.now());
    }

    // 🗓 New Year's Day at 8:00:00 AM
    @Scheduled(cron = "0 0 8 1 1 *")
    public void runNewYearsDay() {
        System.out.println("Happy New Year! Running at: " + LocalDateTime.now());
    }

    // 🎉 Weekends at 6:00:00 PM
    @Scheduled(cron = "0 0 18 * * SAT,SUN")
    public void runWeekendsAt6PM() {
        System.out.println("Running weekends at 6:00 PM: " + LocalDateTime.now());
    }

    // 🕔 5:00 AM on the last day of every month (`L` = last day)
    @Scheduled(cron = "0 0 5 L * *")
    public void runLastDayOfMonth() {
        System.out.println("Running on last day of month at 5:00 AM: " + LocalDateTime.now());
    }

    // 🎆 Once a year on January 1st at midnight
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void runNewYearsMidnight() {
        System.out.println("New Year at midnight! Running at: " + LocalDateTime.now());
    }

    // ⛄ Every day at noon during Jan, Feb, and Mar
    @Scheduled(cron = "0 0 12 * JAN-MAR *")
    public void runQ1DailyAtNoon() {
        System.out.println("Running Q1 daily at noon: " + LocalDateTime.now());
    }

    // 💼 Every 30 minutes between 9 AM and 5 PM on weekdays
    @Scheduled(cron = "0 0/30 9-17 * * MON-FRI")
    public void runBusinessHours() {
        System.out.println("Running during business hours: " + LocalDateTime.now());
    }

    // ⏰ Every second (for testing only - disable in production!)
    @Scheduled(cron = "* * * * * *")
    public void runEverySecond() {
        System.out.println("Running every second at: " + LocalDateTime.now());
        // TODO: Place your logic here (e.g., fetch data, send email, Kafka publish, etc.)
    }
}
@SpringBootApplication
@EnableScheduling
public class HourlyTaskSchedulerApp{
    public static void main(String[] args) {
        SpringApplication.run(HourlyTaskSchedulerApp.class, args);
        // Spring will automatically handle all scheduled tasks
        // No need to manually instantiate the scheduler
    }

}
/**
 * ┌───────────── second (0–59)
 * │ ┌───────────── minute (0–59)
 * │ │ ┌───────────── hour (0–23)
 * │ │ │ ┌───────────── day of month (1–31)
 * │ │ │ │ ┌───────────── month (1–12 or JAN–DEC)
 * │ │ │ │ │ ┌───────────── day of week (0–6 or SUN–SAT)
 * │ │ │ │ │ │
 * │ │ │ │ │ │
 * * * * * * *
 *
 *
 */

//Common Cron Examples
//Schedule	Cron Expression	Description
//Every second	* * * * * *	Runs every second
//Every 5 seconds	*/5 * * * * *	Every 5 seconds
//Every minute	0 * * * * *	At 0 seconds of every minute
//Every hour	0 0 * * * *	At 0 min, 0 sec of every hour
//Every day at 12AM	0 0 0 * * *	Midnight daily
//Every month (1st)	0 0 0 1 * *	At 00:00 on the 1st of each month
//Every Monday	0 0 0 * * MON	At 00:00 every Monday
//Every Sunday 5PM	0 0 17 * * SUN	At 5:00 PM every Sunday
//1st Jan every year	0 0 0 1 1 *	Every New Year's Day at midnight




/**
 * 
 * 
 * Spring Supports 6 Fields (with seconds) ⏰
Spring's @Scheduled cron expression supports 6 fields, which includes the seconds field.

Comparison: 5 vs 6 Field Cron
System	Fields	Format	Example
Unix/Linux Cron	5 fields	minute hour day month weekday	0 12 * * * (noon daily)
Spring @Scheduled	6 fields	second minute hour day month weekday	0 0 12 * * * (noon daily)
Quartz Scheduler	6-7 fields	second minute hour day month weekday [year]	0 0 12 * * ? or 0 0 12 * * ? 2024
Spring Boot Cron Format (6 Fields)
┌───────────── second (0-59)
│ ┌───────────── minute (0-59)
│ │ ┌───────────── hour (0-23)
│ │ │ ┌───────────── day of month (1-31)
│ │ │ │ ┌───────────── month (1-12 or JAN-DEC)
│ │ │ │ │ ┌───────────── day of week (0-7 or SUN-SAT, 0 and 7 = Sunday)
│ │ │ │ │ │
│ │ │ │ │ │
* * * * * *
Key Differences
Unix Cron (5 fields) - Does NOT support seconds
# /etc/crontab or crontab -e
0 12 * * * /path/to/script.sh    # Runs at 12:00:00 (no control over seconds)
Spring Cron (6 fields) - Supports seconds
@Scheduled(cron = "0 0 12 * * *")  // 12:00:00 (you control the seconds)
@Scheduled(cron = "30 0 12 * * *") // 12:00:30 (runs at 30 seconds past noon)
Why Spring Uses 6 Fields?
Spring uses the Quartz Cron format, which needs more precision:

Sub-minute scheduling - Run tasks every 15 seconds:

@Scheduled(cron = "*/15 * * * * *")  // Every 15 seconds
Precise timing - Start at exactly 30 seconds past the hour:

@Scheduled(cron = "30 0 * * * *")  // Every hour at :00:30
Better for application-level tasks - Unlike system cron (which runs scripts), Spring schedules Java methods that need more granular control.

Common Confusion: ? vs *
In Spring/Quartz cron, you'll see ? in day fields:

@Scheduled(cron = "0 0 9 ? * MON")  // Why ? instead of *?
Explanation:

* = "any value"
? = "no specific value" (used when the other day field is specified)
You cannot use * for both day-of-month and day-of-week at the same time
Valid:

@Scheduled(cron = "0 0 12 * * ?")      // Daily at noon (day-of-week is ?)
@Scheduled(cron = "0 0 9 ? * MON")     // Monday at 9 AM (day-of-month is ?)
@Scheduled(cron = "0 0 6 15 * ?")      // 15th of month (day-of-week is ?)
Invalid:

@Scheduled(cron = "0 0 9 * * MON")     // ❌ Error: both day fields specified
Summary
Feature	Unix Cron	Spring @Scheduled
Field Count	5 fields	6 fields
Seconds Support	❌ No	✅ Yes
Minimum Interval	1 minute	1 second
Format	min hr day mon dow	sec min hr day mon dow
Use Case	System-level scripts	Application-level tasks
Special Char	* only	*, ?, L, W, #
Answer: Spring supports 6 fields (including seconds), not 5!
 */