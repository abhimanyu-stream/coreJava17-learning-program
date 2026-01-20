package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class  HourlyTaskScheduler{
    @Scheduled(cron = "0 * * * * *")
// 🔁 Every minute at 0 seconds (e.g., 12:00:00, 12:01:00, 12:02:00...)

    @Scheduled(cron = "0 0 * * * *")
// 🔁 Every hour at 0 minutes and 0 seconds (e.g., 01:00:00, 02:00:00...)

    @Scheduled(cron = "0 0 10 * * *")
// 📅 Every day at 10:00:00 AM

    @Scheduled(cron = "0 30 10 * * *")
// 📅 Every day at 10:30:00 AM

    @Scheduled(cron = "0 0 0 * * *")
// 🌙 Every day at midnight (00:00:00)

    @Scheduled(cron = "0 0 6 1 * *")
// 📆 6:00 AM on the first day of every month

    @Scheduled(cron = "0 0 9 ? * MON")
// 🗓 Every Monday at 9:00:00 AM

    @Scheduled(cron = "0 15 14 * * *")
// 🔔 Every day at 2:15:00 PM

    @Scheduled(cron = "0 0 12 * * MON-FRI")
// 💼 Weekdays (Mon–Fri) at 12:00:00 PM

    @Scheduled(cron = "0 0 0 25 12 *")
// 🎄 Every year on Dec 25th at midnight (Christmas)

    @Scheduled(cron = "0 0/5 * * * *")
// ⏲ Every 5 minutes (e.g., 10:00, 10:05, 10:10...)

    @Scheduled(cron = "0 */10 * * * *")
// 🔄 Every 10 seconds past each minute (e.g., :00, :10, :20...)

    @Scheduled(cron = "*/15 * * * * *")
// 🔂 Every 15 seconds

    @Scheduled(cron = "0 0 23 ? * SUN")
// 🌇 Every Sunday at 11:00:00 PM

    @Scheduled(cron = "0 0 8 1 1 *")
// 🗓 New Year's Day at 8:00:00 AM

    @Scheduled(cron = "0 0 18 * * SAT,SUN")
// 🎉 Weekends at 6:00:00 PM

    @Scheduled(cron = "0 0 5 L * *")
// 🕔 5:00 AM on the last day of every month (`L` = last day)

    @Scheduled(cron = "0 0 0 1 1 ?")
// 🎆 Once a year on January 1st at midnight

    @Scheduled(cron = "0 0 12 * JAN-MAR *")
// ⛄ Every day at noon during Jan, Feb, and Mar

    @Scheduled(cron = "0 0/30 9-17 * * MON-FRI")
// 💼 Every 30 minutes between 9 AM and 5 PM on weekdays

    @Scheduled(cron = "* * * * * *") // ⏰ Every second
    public void runHourlyTask() {
        System.out.println("Running hourly task at: " + LocalDateTime.now());
        // TODO: Place your logic here (e.g., fetch data, send email, Kafka publish, etc.)
    }
}
@SpringBootApplication
@EnableScheduling
public class HourlyTaskSchedulerApp{
    public static void main(String[] args) {
        SpringApplication.run(HourlyTaskSchedulerApp.class, args);
        HourlyTaskScheduler h = new HourlyTaskScheduler();
        h.runHourlyTask();
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