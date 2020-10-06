package net.nodium.games.paul.gl;

import net.nodium.games.paul.input.KeyHandler;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Display {
    private long windowID;
    private int width;
    private int height;
    private String title;
    private KeyHandler keyHandler;

    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void createWindow() {
// setup error callback. default implementation prints to System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // init GLFW. most GLFW functions will not work before doing this
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // configure window
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // create window
        windowID = glfwCreateWindow(width, height, title, NULL, NULL);

        if (windowID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // setup a key callback, called every time a key is pressed, repeated or released
        // TODO move this to its own class
//        glfwSetKeyCallback(windowID, new GLFWKeyCallbackI() {
//            @Override
//            public void invoke(long window, int key, int scancode, int action, int mods) {
//                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
//                    glfwSetWindowShouldClose(window, true); // the main loop detects this and ends when it's true
//                }
//            }
//        });
        glfwSetKeyCallback(windowID, keyHandler);

        // get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // get the window size passed to glfwCreateWindow
            glfwGetWindowSize(windowID, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    windowID,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // make the OpenGL context current
        glfwMakeContextCurrent(windowID);
        // enable vsync
        glfwSwapInterval(1);

        // make the window visible
        glfwShowWindow(windowID);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    public Display disableCursor() {
        glfwSetInputMode(this.getWindowID(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
        return this;
    }

    public Display enableCursor() {
        glfwSetInputMode(this.getWindowID(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        return this;
    }

    public long getWindowID() {
        return windowID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
