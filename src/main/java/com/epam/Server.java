package com.epam;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.constants.CommonConstants;
import com.epam.handler.Handler;
import com.epam.handler.IHandle;
import com.epam.handler.imphandler.DefHandler;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.utils.MatcherUtils;
import com.epam.utils.SplitUtils;

public class Server {

	private final List<Handler> handlers = new ArrayList<Handler>();
	private ExecutorService pool;
	private int port;
	private int sizeOfTreadPool;

	public Server() {
	}

	public Server(int port, int sizeOfThreadPool) {
		this.port = port;
		this.sizeOfTreadPool = sizeOfThreadPool;
	}

	@SuppressWarnings("resource")
	public void start() throws IOException {
		pool = Executors.newFixedThreadPool(sizeOfTreadPool);
		ServerSocket serSocket = new ServerSocket(port);
		while (true) {
			Socket sock = serSocket.accept();
			pool.submit(new SocketProcessor(sock));
		}
	}

	public void stop() {
		pool.shutdown();
	}

	public Handler findHandler(Request rq) throws IOException {
		Handler defHandler = new Handler(null, null, new DefHandler());
		String metnodFromRequest = rq.getMethod();
		String pathFromRequest = rq.getPath();

		for (Handler handler : handlers) {
			if (metnodFromRequest.equals(handler.getMethod())) {
				return handler;
			}
		}
		return defHandler;
	}

	public void addHandler(String method, String path, IHandle handler) {
		handlers.add(new Handler(method, path, handler));
	}

	private class SocketProcessor implements Runnable {

		private Request rq;
		private Response rp;
		private Socket socket;

		public SocketProcessor(Socket socket) {
			this.socket = socket;
		}

		public void run() {

			Request rq = new Request();
			try {
				InputStream sin = socket.getInputStream();
				DataInputStream in = new DataInputStream(sin);
				String line = in.readUTF();
				String[] clientRq = line.split("\n");
				rq = new Request(clientRq);


				rp = new Response(socket.getOutputStream());

			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Handler handlerForInvoke = findHandler(rq);
				String s = handlerForInvoke.getiHandle().handle(rq, rp);
				//System.out.println(s);
				OutputStream sout = socket.getOutputStream();
				DataOutputStream out = new DataOutputStream(sout);
				out.writeUTF(s);
			} catch (IOException e1) {
			}
			try {
				this.socket.close();
			} catch (IOException e) {
			}
		}
	}
}
