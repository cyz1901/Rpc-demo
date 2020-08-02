class TestServer {

}
object TestServer{
  def main(args: Array[String]): Unit = {
    val man = new Man
    RpcFramework.`export`(man,5050)
  }
}
