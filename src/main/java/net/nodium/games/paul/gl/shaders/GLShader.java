package net.nodium.games.paul.gl.shaders;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryStack;

import java.io.*;
import java.nio.FloatBuffer;

public abstract class GLShader {
    private int programID;
    private int vshID;
    private int fshID;

    private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public GLShader(String vshPath, String fshPath) {
        vshID = loadShader(vshPath, GL20.GL_VERTEX_SHADER);
        fshID = loadShader(fshPath, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vshID);
        GL20.glAttachShader(programID, fshID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String name) {
        return GL20.glGetUniformLocation(programID, name);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector2f(int location, Vector2f vector) {
        GL20.glUniform2f(location, vector.x, vector.y);
    }

    protected void loadVector3f(int location, Vector3f vector) {
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadVector4f(int location, Vector4f vector) {
        GL20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
    }

    protected void loadBoolean(int location, boolean value) {
        GL20.glUniform1i(location, value ? 1 : 0);
    }

    protected void loadMatrix4f(int location, Matrix4f matrix) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            matrix.get(buffer);
            GL20.glUniformMatrix4fv(location, false, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        GL20.glUseProgram(programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        stop();
        GL20.glDetachShader(programID, vshID);
        GL20.glDetachShader(programID, fshID);
        GL20.glDeleteShader(vshID);
        GL20.glDeleteShader(fshID);
        GL20.glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    private static int loadShader(String path, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            InputStream in = Class.class.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Couldn't read shader");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Couldn't compile shader");
            System.exit(-1);
        }
        return shaderID;
    }

    public int getProgramID() {
        return programID;
    }
}