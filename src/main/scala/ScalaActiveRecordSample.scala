import java.sql.Timestamp
import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

object ScalaActiveRecordSample {

  case class User(
    @Column("NAME") var name: String,
    var lastLogin: Option[Timestamp] // mapped "last_login" column
  ) extends ActiveRecord // column id is already implemented

  object User extends ActiveRecordCompanion[User]

}

object Tables extends ActiveRecordTables {
  val user = table[ScalaActiveRecordSample.User] // mapped "users" table]
}
