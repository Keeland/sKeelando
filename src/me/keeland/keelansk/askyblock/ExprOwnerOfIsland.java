package me.keeland.keelansk.askyblock;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

public class ExprOwnerOfIsland extends SimpleExpression<Player>{

    private Expression<Location> location;

    public Class<? extends Player> getReturnType() {

        return Player.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.location = (Expression<Location>) args[0];
        return true;
    }

    @Override
    public String toString(Event arg0, boolean arg1) {
        return "return owner of a island at a location";
    }

    @Override
    @Nullable
    protected Player[] get(Event arg0) {
        Location loc = this.location.getSingle(arg0);
        UUID r = null;
        Player s = null;
        	
        r = ASkyBlockAPI.getInstance().getOwner(loc);
       	s = Bukkit.getPlayer(r);

        if (s == null){
            return null;
        }

        return new Player[] { s };
    }
}