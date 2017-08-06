package domains.reminder.schedule

import java.util.Comparator

import scala.math.Ordering

case object ScheduleTimeParser {
  val pattern = new scala.util.matching.Regex("""(\d\d):(\d\d)""", "hour", "minute")

  def parse(formattedString: String): (Int, Int) = {
    var hour = -1
    var minute = -1
    pattern.findAllIn(formattedString).matchData.foreach { m=>
      hour = m.group("hour").toInt
      minute = m.group("minute").toInt
    }
    (hour, minute)
  }
}

class ScheduleTime(hour: Int, minute: Int) {
  def this(tuple: (Int, Int)) = this(tuple._1, tuple._2)
  def this(formattedString: String) = this(ScheduleTimeParser.parse(formattedString))

  override def toString: String = {
    "%s: %02d:%02d".format(this.getClass.getName, scheduleHour.value, scheduleMinute.value)
  }

  val scheduleHour: ScheduleHour = new ScheduleHour(hour)
  val scheduleMinute: ScheduleMinute = new ScheduleMinute(minute)

  def value: (Int, Int) = (scheduleHour.value, scheduleMinute.value)

}

case object ScheduleTimeSelection {
  val choices: Seq[ScheduleTime] = {
    val ordering = new Ordering[ScheduleTime] {
      private val c1 = new Comparator[ScheduleTime] {
        def compare(x: ScheduleTime, y: ScheduleTime): Int = x.scheduleHour.value.compareTo(y.scheduleHour.value)
      }
      private val c2 = new Comparator[ScheduleTime] {
        def compare(x: ScheduleTime, y: ScheduleTime): Int = x.scheduleMinute.value.compareTo(y.scheduleMinute.value)
      }
      private val c: Comparator[ScheduleTime] = c1.thenComparing(c2)
      def compare(x: ScheduleTime, y: ScheduleTime): Int = c.compare(x,y)
    }

    ScheduleHour.choices.flatMap { hour =>
      ScheduleMinute.choices.map { minute =>
        new ScheduleTime(hour, minute)
      }
    }.toSeq.sorted(ordering)
  }
}
