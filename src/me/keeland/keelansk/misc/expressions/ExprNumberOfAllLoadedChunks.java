package me.keeland.keelansk.misc.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;

public class ExprNumberOfAllLoadedChunks extends SimpleExpression<Integer> {

    protected Integer[] get(Event event) {
		int j = 0;
		for (World world : Bukkit.getWorlds()) {
			j = j + world.getLoadedChunks().length;
		}
        return new Integer[] { j };
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    public String toString(Event event, boolean b) {
        return this.getClass().getName();
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}	