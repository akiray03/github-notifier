package domains.reminder

import domains.reminder.schedule.ScheduleTime


case object ReminderRule {
  val label: String = ""
  val scheduleList: List[ScheduleTime] = List.empty[ScheduleTime]
}
