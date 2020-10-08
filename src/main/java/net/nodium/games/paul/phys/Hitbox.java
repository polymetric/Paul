package net.nodium.games.paul.phys;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.GLUtils;
import net.nodium.games.paul.gl.shaders.GLShaderDebug;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL46;

import javax.smartcardio.Card;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Hitbox {
    public Vector3f pos;
    public Vector3f size = new Vector3f();
    public Vector3f offset = new Vector3f();
    private Bounds bounds = new Bounds();

    private boolean boundsCalculatedThisTick = false;

    public Hitbox(Vector3f pos, float sizeX, float sizeY, float sizeZ) {
        this.pos = pos;
        size.x = sizeX;
        size.y = sizeY;
        size.z = sizeZ;
    }

    public void tick() {
        boundsCalculatedThisTick = false;
    }

    public Bounds getBounds() {
        if (boundsCalculatedThisTick) return bounds;

        bounds.min.x = pos.x - size.x / 2 + offset.x;
        bounds.min.y = pos.y - size.y / 2 + offset.y;
        bounds.min.z = pos.z - size.z / 2 + offset.z;

        bounds.max.x = pos.x + size.x / 2 + offset.x;
        bounds.max.y = pos.y + size.y / 2 + offset.y;
        bounds.max.z = pos.z + size.z / 2 + offset.z;

        boundsCalculatedThisTick = true;
        return bounds;
    }

    public boolean intersectsWith(Hitbox other) {
        Bounds a = this.getBounds();
        Bounds b = other.getBounds();

        float d1x = b.min.x - a.max.x;
        float d1y = b.min.y - a.max.y;
        float d1z = b.min.z - a.max.z;

        float d2x = a.min.x - b.max.x;
        float d2y = a.min.y - b.max.y;
        float d2z = a.min.z - b.max.z;
        
        if (d1x > 0f || d1y > 0f || d1z > 0f) { return false; }
        if (d2x > 0f || d2y > 0f || d2z > 0f) { return false; }

        return true;
    }

    public CardinalDirection intersectDir(Hitbox other) {
        Bounds a = this.getBounds();
        Bounds b = other.getBounds();

        float d1x = b.min.x - a.max.x;
        float d1y = b.min.y - a.max.y;
        float d1z = b.min.z - a.max.z;

        float d2x = a.min.x - b.max.x;
        float d2y = a.min.y - b.max.y;
        float d2z = a.min.z - b.max.z;

        return CardinalDirection.NEG_X;
    }

    private static int vao = GL46.glGenVertexArrays();
    private static int vboVertices = GL46.glGenBuffers();
    private static int vboIndices = GL46.glGenBuffers();
    private static GLShaderDebug shader = new GLShaderDebug();

    public void render(GLShaderDebug shader123, Matrix4f projMatrix, Camera camera) {
        shader.start();

        GL46.glBindVertexArray(vao);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, vboVertices);
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, vboIndices);
        
        float minX = bounds.min.x;
        float minY = bounds.min.y;
        float minZ = bounds.min.z;
        float maxX = bounds.max.x;
        float maxY = bounds.max.y;
        float maxZ = bounds.max.z;

//      FloatBuffer vertices = GLUtils.storeDataInFloatBuffer(new float[] {
//              minX, minY, minZ,
//              maxX, maxY, maxZ,
//      });
        
        FloatBuffer vertices = GLUtils.storeDataInFloatBuffer(new float[] {
                minX, minY, minZ, // 0
                minX, minY, maxZ, // 1
                maxX, minY, maxZ, // 2
                maxX, minY, minZ, // 3
                minX, maxY, minZ, // 4
                minX, maxY, maxZ, // 5
                maxX, maxY, maxZ, // 6
                maxX, maxY, minZ, // 7
        });

        IntBuffer indices = GLUtils.storeDataInIntBuffer(new int[] {
                0, 1,
                1, 2,
                2, 3,
                3, 0,

                0, 4,
                1, 5,
                2, 6,
                3, 7,

                4, 5,
                5, 6,
                6, 7,
                7, 4,
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
                GL46.GL_FLOAT,      // type
                false,              // normalized
                0,                  // stride
                0                   // offset
        );
        shader.loadProjMatrix(projMatrix);
        shader.loadViewMatrix(camera);
        shader.loadTransMatrix(new Matrix4f());

        shader.loadColor(new Vector4f(1, 0, 1, 1));

        GL46.glEnableVertexAttribArray(0);

//        GL46.glPointSize(10);
//        GL46.glDrawArrays(GL46.GL_POINTS, 0, 6);
//        GL46.glDrawArrays(GL46.GL_LINE_STRIP, 0, 8);
        GL46.glLineWidth(5);
        GL46.glDisable(GL46.GL_DEPTH_TEST);
        GL46.glDrawElements(GL46.GL_LINES, 24, GL46.GL_UNSIGNED_INT, 0);
        GL46.glEnable(GL46.GL_DEPTH_TEST);

        GL46.glDisableVertexAttribArray(0);
        GL46.glBindVertexArray(0);
        shader.stop();
    }

    public Hitbox setOffset(float x, float y, float z) {
        offset.x = x;
        offset.y = y;
        offset.z = z;

        return this;
    }

    public class Bounds {
        public Vector3f min = new Vector3f();
        public Vector3f max = new Vector3f();

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
}
