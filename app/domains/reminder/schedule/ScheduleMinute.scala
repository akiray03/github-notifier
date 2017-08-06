package domains.reminder.schedule

import scala.collection.immutable.SortedSet

case object ScheduleMinute {
  val choices: SortedSet[Int] = SortedSet(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55)
}

class ScheduleMinute(minute: Int) {
  require(ScheduleMinute.choices.contains(minute))
  val value: Int = minute

  override def toString: String = "%s: %d".format(this.getClass.getName, value)
}
