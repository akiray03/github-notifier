package domains.reminder.schedule

case class Schedule(scheduleTime: ScheduleTime, dayOfWeekSet: DayOfWeekSet) {
  val time: ScheduleTime = scheduleTime
  val days: DayOfWeekSet = dayOfWeekSet
}
