import java.sql.Timestamp
import scalikejdbc._
import skinny.orm._

object SkinnyORMSample {

  case class User(id: Long, name: String, lastLogin: Option[Timestamp])

  object User extends SkinnyCRUDMapper[User] {
    override val tableName = "USERS"
    override lazy val defaultAlias = createAlias("m")
    override def extract(rs: WrappedResultSet, n: ResultName[User]) = autoConstruct(rs, n)
  }
  
}
