package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWVidMode;

import input.CursorInput;



public class Main implements Runnable{
	
	private boolean running = false;
	private Thread thread;
	
	private long window;
	
	private int winSize;
	
	GLFWCursorPosCallback cursorPos;
	
	public static void main(String[] args) {
		new Main().start();
	}
	
	private void start() {
		
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
		
	}

	public void run() {
		
		init();
		
		while(running){
			
			update();
			render();
			
			if(glfwWindowShouldClose(window)){
				running = false;
			}
			
		}
		
	}

	private void init() {
		
		if(!glfwInit()){
			
			// TODO: handle GL not initializing error
			
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		winSize = (vidmode.height()/4)*3;
		window = glfwCreateWindow(winSize, winSize, "Tic-tac-toe", NULL, NULL);
		
		if(window == NULL){
			// TODO: handle non initialized window error
			return;
		}
		
		glfwSetWindowPos(window, (vidmode.width() - winSize)/2, (vidmode.height() - winSize)/2 );
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		glfwSetCursorPosCallback(window, cursorPos = new CursorInput());
		
	}
	
	private void update() {
		glfwPollEvents();
	}
	
	private void render() {
		glfwSwapBuffers(window);
	}
	
}
