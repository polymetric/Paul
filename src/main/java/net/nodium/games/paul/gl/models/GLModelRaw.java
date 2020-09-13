package net.nodium.games.paul.gl.models;

public class GLModelRaw {
    private int vaoID;
    private int vertexCount;

    public GLModelRaw(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
