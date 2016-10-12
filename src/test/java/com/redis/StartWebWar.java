package com.redis;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车停止服务.
 */
public class StartWebWar {

	public static final int PORT = 8100;
	public static final String CONTEXT = "";
	public static final String BASE_URL = "http://localhost:8100";


	public static void main(String[] args) throws Exception {
		// System.setProperty("configFile", "/Users/xjli/managerui-biz-startup/up-war/src/main/resources/config/main-conf.properties");
		Server server = new Server(PORT);
		WebAppContext webContext = new WebAppContext("src/main/webapp/", CONTEXT);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		server.start();
		System.out.println("Server running at " + BASE_URL);
		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}

//    public static void main(String[] args) {
//        String str = "4edd447e-f4f2-4c80-a5e7-c36d7af4e54a";
//        int code = Math.abs(str.toLowerCase().hashCode());
//        System.out.println(code+","+code%2+","+code%256);
//
//
//
//    }

    /*public static void main(String[] args) {
		try {
            System.out.println(InetAddress.getLocalHost().getHostAddress() );
        }catch (Exception e){
        }

    }*/


}
