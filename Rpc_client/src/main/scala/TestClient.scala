class TestClient {

}
object TestClient{
  def main(args: Array[String]): Unit = {
    val cc:Man= RpcFramework.refer(Man,"127.0.0.1",5050)
    val sum = cc.add()
  }
}
