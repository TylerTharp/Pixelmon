package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Packet51MapChunk extends Packet
{
    /** The x-position of the transmitted chunk, in chunk coordinates. */
    public int xCh;

    /** The z-position of the transmitted chunk, in chunk coordinates. */
    public int zCh;

    /**
     * The y-position of the lowest chunk Section in the transmitted chunk, in chunk coordinates.
     */
    public int yChMin;

    /**
     * The y-position of the highest chunk Section in the transmitted chunk, in chunk coordinates.
     */
    public int yChMax;

    /** The transmitted chunk data, decompressed. */
    public byte[] chunkData;

    /**
     * Whether to initialize the Chunk before applying the effect of the Packet51MapChunk.
     */
    public boolean includeInitialize;

    /** The length of the compressed chunk data byte array. */
    private int tempLength;
    private int field_48110_h;

    /** A temporary storage for the compressed chunk data byte array. */
    private static byte[] temp = new byte[0];

    public Packet51MapChunk()
    {
        this.isChunkDataPacket = true;
    }

    public Packet51MapChunk(Chunk par1Chunk, boolean par2, int par3)
    {
        this.isChunkDataPacket = true;
        this.xCh = par1Chunk.xPosition;
        this.zCh = par1Chunk.zPosition;
        this.includeInitialize = par2;

        if (par2)
        {
            par3 = 65535;
            par1Chunk.field_50025_o = true;
        }

        ExtendedBlockStorage[] var4 = par1Chunk.getBlockStorageArray();
        int var5 = 0;
        int var6 = 0;
        int var7;

        for (var7 = 0; var7 < var4.length; ++var7)
        {
            if (var4[var7] != null && (!par2 || !var4[var7].getIsEmpty()) && (par3 & 1 << var7) != 0)
            {
                this.yChMin |= 1 << var7;
                ++var5;

                if (var4[var7].getBlockMSBArray() != null)
                {
                    this.yChMax |= 1 << var7;
                    ++var6;
                }
            }
        }

        var7 = 2048 * (5 * var5 + var6);

        if (par2)
        {
            var7 += 256;
        }

        if (temp.length < var7)
        {
            temp = new byte[var7];
        }

        byte[] var8 = temp;
        int var9 = 0;
        int var10;

        for (var10 = 0; var10 < var4.length; ++var10)
        {
            if (var4[var10] != null && (!par2 || !var4[var10].getIsEmpty()) && (par3 & 1 << var10) != 0)
            {
                byte[] var11 = var4[var10].func_48590_g();
                System.arraycopy(var11, 0, var8, var9, var11.length);
                var9 += var11.length;
            }
        }

        NibbleArray var15;

        for (var10 = 0; var10 < var4.length; ++var10)
        {
            if (var4[var10] != null && (!par2 || !var4[var10].getIsEmpty()) && (par3 & 1 << var10) != 0)
            {
                var15 = var4[var10].func_48594_i();
                System.arraycopy(var15.data, 0, var8, var9, var15.data.length);
                var9 += var15.data.length;
            }
        }

        for (var10 = 0; var10 < var4.length; ++var10)
        {
            if (var4[var10] != null && (!par2 || !var4[var10].getIsEmpty()) && (par3 & 1 << var10) != 0)
            {
                var15 = var4[var10].getBlocklightArray();
                System.arraycopy(var15.data, 0, var8, var9, var15.data.length);
                var9 += var15.data.length;
            }
        }

        for (var10 = 0; var10 < var4.length; ++var10)
        {
            if (var4[var10] != null && (!par2 || !var4[var10].getIsEmpty()) && (par3 & 1 << var10) != 0)
            {
                var15 = var4[var10].getSkylightArray();
                System.arraycopy(var15.data, 0, var8, var9, var15.data.length);
                var9 += var15.data.length;
            }
        }

        if (var6 > 0)
        {
            for (var10 = 0; var10 < var4.length; ++var10)
            {
                if (var4[var10] != null && (!par2 || !var4[var10].getIsEmpty()) && var4[var10].getBlockMSBArray() != null && (par3 & 1 << var10) != 0)
                {
                    var15 = var4[var10].getBlockMSBArray();
                    System.arraycopy(var15.data, 0, var8, var9, var15.data.length);
                    var9 += var15.data.length;
                }
            }
        }

        if (par2)
        {
            byte[] var17 = par1Chunk.getBiomeArray();
            System.arraycopy(var17, 0, var8, var9, var17.length);
            var9 += var17.length;
        }

        Deflater var16 = new Deflater(-1);

        try
        {
            var16.setInput(var8, 0, var9);
            var16.finish();
            this.chunkData = new byte[var9];
            this.tempLength = var16.deflate(this.chunkData);
        }
        finally
        {
            var16.end();
        }
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.xCh = par1DataInputStream.readInt();
        this.zCh = par1DataInputStream.readInt();
        this.includeInitialize = par1DataInputStream.readBoolean();
        this.yChMin = par1DataInputStream.readShort();
        this.yChMax = par1DataInputStream.readShort();
        this.tempLength = par1DataInputStream.readInt();
        this.field_48110_h = par1DataInputStream.readInt();

        if (temp.length < this.tempLength)
        {
            temp = new byte[this.tempLength];
        }

        par1DataInputStream.readFully(temp, 0, this.tempLength);
        int var2 = 0;
        int var3;

        for (var3 = 0; var3 < 16; ++var3)
        {
            var2 += this.yChMin >> var3 & 1;
        }

        var3 = 12288 * var2;

        if (this.includeInitialize)
        {
            var3 += 256;
        }

        this.chunkData = new byte[var3];
        Inflater var4 = new Inflater();
        var4.setInput(temp, 0, this.tempLength);

        try
        {
            var4.inflate(this.chunkData);
        }
        catch (DataFormatException var9)
        {
            throw new IOException("Bad compressed data format");
        }
        finally
        {
            var4.end();
        }
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(this.xCh);
        par1DataOutputStream.writeInt(this.zCh);
        par1DataOutputStream.writeBoolean(this.includeInitialize);
        par1DataOutputStream.writeShort((short)(this.yChMin & 65535));
        par1DataOutputStream.writeShort((short)(this.yChMax & 65535));
        par1DataOutputStream.writeInt(this.tempLength);
        par1DataOutputStream.writeInt(this.field_48110_h);
        par1DataOutputStream.write(this.chunkData, 0, this.tempLength);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler par1NetHandler)
    {
        par1NetHandler.handleMapChunk(this);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 17 + this.tempLength;
    }
}
