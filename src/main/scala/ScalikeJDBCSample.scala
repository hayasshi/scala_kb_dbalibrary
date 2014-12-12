import java.sql.Timestamp

import scalikejdbc._

object ScalikeJDBCSample {

  case class User(id: Long, name: String, lastLogin: Option[Timestamp])

  object User extends SQLSyntaxSupport[User] {
    override val tableName = "USERS"
    def apply(rn: ResultName[User])(rs: WrappedResultSet): User =
      new User(id = rs.get(rn.id), name = rs.get(rn.name), lastLogin = rs.get(rn.lastLogin))
  }
  
}