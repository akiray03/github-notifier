package domains.reminder.schedule

import scala.collection.immutable.SortedSet

case object ScheduleHour {
  val choices: SortedSet[Int] = (0 to 23).to[SortedSet]
}

class ScheduleHour(hour: Int) {
  require(ScheduleHour.choices.contains(hour))
  val value: Int = hour

  override def toString: String = "%s: %d".format(this.getClass.getName, value)
}
