package dark.assembly.common.armbot.command;

import com.builtbroken.common.science.units.UnitHelper;

import universalelectricity.core.vector.Vector3;
import dark.api.al.armbot.Command;
import dark.api.al.armbot.IArmbot;
import dark.api.al.armbot.IArmbotTask.TaskType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommandIdle extends Command
{

    /** The amount of time in which the machine will idle. */
    public int idleTime = 80;
    private int totalIdleTime = 80;

    public CommandIdle()
    {
        super("wait", TaskType.DEFINEDPROCESS);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onMethodCalled(World world, Vector3 location, IArmbot armbot)
    {
        super.onMethodCalled(world, location, armbot);

        if (UnitHelper.tryToParseInt("" + this.getArg(0)) > 0)
        {
            this.idleTime = UnitHelper.tryToParseInt("" + this.getArg(0));
            this.totalIdleTime = this.idleTime;
            return true;
        }
        return false;
    }

    @Override
    public boolean onUpdate()
    {
        /** Randomly move the arm to simulate life in the arm if the arm is powered */
        // this.tileEntity.rotationPitch *= 0.98 * this.world.rand.nextFloat();

        if (this.idleTime > 0)
        {
            this.idleTime--;
            return true;
        }

        return false;
    }

    @Override
    public Command loadProgress(NBTTagCompound taskCompound)
    {
        super.loadProgress(taskCompound);
        this.idleTime = taskCompound.getInteger("idleTime");
        this.totalIdleTime = taskCompound.getInteger("idleTotal");
        return this;
    }

    @Override
    public NBTTagCompound saveProgress(NBTTagCompound taskCompound)
    {
        super.saveProgress(taskCompound);
        taskCompound.setInteger("idleTime", this.idleTime);
        taskCompound.setInteger("idleTotal", this.totalIdleTime);
        return taskCompound;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + Integer.toString(this.totalIdleTime);
    }

    @Override
    public Command clone()
    {
        return new CommandIdle();
    }

}
