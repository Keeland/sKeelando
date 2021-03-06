package me.keeland.keelansk.towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class ExprNationOfPlayer extends SimpleExpression<Nation>{

    private Expression<Player> player;

    public Class<? extends Nation> getReturnType() {

        return Nation.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.player = (Expression<Player>) args[0];
        return true;
    }

    @Override
    public String toString(Event arg0, boolean arg1) {
        return "return nation of player";
    }

    @Override
    protected Nation[] get(Event arg0) {
        String p = this.player.getSingle(arg0).getName().toString();
        Nation r = null;
        try {
            r = TownyUniverse.getDataSource().getResident(p).getTown().getNation();
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }

        if (r == null){
            return null;
        }

        return new Nation[] { r };
    }

}
