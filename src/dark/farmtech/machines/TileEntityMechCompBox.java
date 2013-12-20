package dark.farmtech.machines;

import java.util.ArrayList;
import java.util.List;

import com.dark.prefab.tile.network.NetworkSharedPower;
import com.dark.tile.network.INetworkEnergyPart;
import com.dark.tile.network.ITileNetwork;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/** Advanced version of the compBox that can link to other boxes and process matter at a higher rate.
 * Cost some minor power and will have some mechanical animation of flipping dirt & items
 * 
 * @author DarkGuardsman */
public class TileEntityMechCompBox extends TileEntityCompBox implements INetworkEnergyPart
{
    List<TileEntity> connections = new ArrayList<TileEntity>();

    public TileEntityMechCompBox()
    {
        this.MAX_JOULES_STORED = 100;
        this.JOULES_PER_TICK = .001f; //1w
    }

    @Override
    public List<TileEntity> getNetworkConnections()
    {
        if (this.connections == null)
        {
            this.connections = new ArrayList<TileEntity>();
        }
        return this.connections;
    }

    @Override
    public void refresh()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public NetworkSharedPower getTileNetwork()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTileNetwork(ITileNetwork fluidNetwok)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canTileConnect(Connection type, ForgeDirection dir)
    {
        return type != null && type == Connection.NETWORK;
    }

    @Override
    public float getPartEnergy()
    {
        return this.energyStored;
    }

    @Override
    public float getPartMaxEnergy()
    {
        return this.MAX_JOULES_STORED;
    }

    @Override
    public void setPartEnergy(float energy)
    {
        this.energyStored = energy;
    }
}