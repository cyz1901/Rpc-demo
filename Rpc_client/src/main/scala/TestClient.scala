class TestClient {

}
object TestClient{
  def main(args: Array[String]): Unit = {
    val cc = RpcFramework.refer(Man,"127.0.0.1",5051).asInstanceOf[People]
    val i = cc.add(1,2)
    println("远程响应：" + i)


  }
}
