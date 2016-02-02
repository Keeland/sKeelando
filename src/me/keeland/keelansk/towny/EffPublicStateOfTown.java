package me.keeland.keelansk.towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.object.Town;

public class EffPublicStateOfTown extends Effect {
	
    private Expression<Town> town;
    private Expression<Boolean> br;

    protected void execute(Event event) {
        Town town = this.town.getSingle(event);
        Boolean br = this.br.getSingle(event);
        if (town == null || br == null) return;
        town.setPublic(br);
        return;
    }

    public String toString(Event event, boolean bool) {
        return this.getClass().getName();
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.town = (Expression<Town>) expressions[0];
        this.br = (Expression<Boolean>) expressions[1];
        return true;
    }
}