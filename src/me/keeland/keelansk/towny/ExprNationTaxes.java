package me.keeland.keelansk.towny;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.TownyUniverse;


public class ExprNationTaxes extends SimpleExpression<Double>{

    private Expression<String> nation;

    public Class<? extends Double> getReturnType() {

        return Double.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.nation = (Expression<String>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return taxes of a nation";
    }

    @Override
    @javax.annotation.Nullable
    protected Double[] get(Event arg0) {
        String n = this.nation.getSingle(arg0);
        Nation tw = null;
        try {
            tw = TownyUniverse.getDataSource().getNation(n);
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }

        if (tw == null){
            return null;
        }

        Double i = tw.getTaxes();

        return new Double[] { i };
    }
    
    @Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Nation nwc = null;
		try {
			nwc = TownyUniverse.getDataSource().getNation(this.nation.getSingle(e));
		} catch (NotRegisteredException e1) {
			e1.printStackTrace();
		}
		if (nwc == null)
			return;
		Integer taxes = (Integer) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			nwc.setTaxes(taxes.doubleValue());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			return CollectionUtils.array(Double.class);
		}
		return null;
	}

}