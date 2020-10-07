package net.nodium.games.paul.phys;

import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.GLUtils;
import net.nodium.games.paul.gl.shaders.GLShaderDebug;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL46;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Hitbox {
    public Vector3f pos;
    public Vector3f size = new Vector3f();
    public Bounds bounds = new Bounds();

    public Hitbox(Vector3f pos, float sizeX, float sizeY, float sizeZ) {
        this.pos = pos;
        size.x = sizeX;
        size.y = sizeY;
        size.z = sizeZ;
    }

    public void calcBounds() {
        bounds.min.x = pos.x - size.x / 2;
        bounds.min.y = pos.y - size.y / 2;
        bounds.min.z = pos.z - size.z / 2;

        bounds.max.x = pos.x + size.x / 2;
        bounds.max.y = pos.y + size.y / 2;
        bounds.max.z = pos.z + size.z / 2;
    }

    public Intersection intersectsWith(Hitbox other) {
        this.calcBounds();
        other.calcBounds();

        Intersection inter = new Intersection();

        if (bounds.max.x > other.bounds.min.x && bounds.min.x < other.bounds.max.x) inter.onX = true;
        if (bounds.max.y > other.bounds.min.y && bounds.min.y < other.bounds.max.y) inter.onY = true;
        if (bounds.max.z > other.bounds.min.z && bounds.min.z < other.bounds.max.z) inter.onZ = true;

        return inter;
    }

    private static int vao = GL30.glGenVertexArrays();
    private static int vbo = GL30.glGenVertexArrays();
    private static GLShaderDebug shader = new GLShaderDebug();

    public void render(GLShaderDebug shader123, Matrix4f projMatrix, Camera camera) {
        shader.start();

        GL46.glBindVertexArray(vao);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, vbo);

        FloatBuffer vertices = GLUtils.storeDataInFloatBuffer(new float[] {
                bounds.min.x, bounds.min.y, bounds.min.z,
                bounds.max.x, bounds.max.y, bounds.max.z,
        });

        IntBuffer indices = GLUtils.storeDataInIntBuffer(new int[] {

        });

        GL46.glBufferData(
                GL46.GL_ARRAY_BUFFER,
                vertices,
                GL46.GL_STATIC_DRAW
        );
        GL46.glBufferData(
                GL46.GL_ELEMENT_ARRAY_BUFFER,
                indices,
                GL46.GL_STATIC_DRAW
        );
        GL46.glVertexAttribPointer(
                0,                  // index
                3,                  // size
                GL11.GL_FLOAT,      // type
                false,              // normalized
                0,                  // stride
                0                   // offset
        );
        shader.loadProjMatrix(projMatrix);
        shader.loadViewMatrix(camera);
        shader.loadTransMatrix(new Matrix4f());

        shader.loadColor(new Vector4f(0, 1, 0, 1));
        GL46.glEnableVertexAttribArray(0);

        GL46.glPointSize(10);

        GL46.glDrawArrays(GL11.GL_POINTS, 0, 6);
//        GL46.glDrawElements();
    }

    public class Bounds {
        Vector3f min = new Vector3f();
        Vector3f max = new Vector3f();

        public Bounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            min.x = minX;
            min.y = minY;
            min.z = minZ;

            max.x = maxX;
            max.y = maxY;
            max.z = maxZ;
        }

        public Bounds() {

        }

        public String toString() {
            return String.format(
                    "%12.3f %12.3f %12.3f %12.3f %12.3f %12.3f ",
                    min.x,
                    min.y,
                    min.z,
                    max.x,
                    max.y,
                    max.z
            );
        }
    }

    public class Intersection {
        public boolean onX = false;
        public boolean onY = false;
        public boolean onZ = false;
    }
}
