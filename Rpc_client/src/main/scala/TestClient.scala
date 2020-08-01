class TestClient {

}
object TestClient{
  def main(args: Array[String]): Unit = {
    val cc = RpcFramework.refer(Man,"127.0.0.1",5050).asInstanceOf[People]
    println(cc.add(1,2))

  }
}
