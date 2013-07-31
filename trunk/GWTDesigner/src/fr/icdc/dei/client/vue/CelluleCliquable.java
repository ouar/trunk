package fr.icdc.dei.client.vue;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

import fr.icdc.dei.client.AccueilPresenter;

/**
 * A {@link Cell} used to render a button.
 */
public class CelluleCliquable extends AbstractSafeHtmlCell<String> {
	/**
	 * Presenter de l'écran avec le tableau.
	 */
	private AccueilPresenter presenter;

	public CelluleCliquable(AccueilPresenter presenter) {
		this(SimpleSafeHtmlRenderer.getInstance());
		this.presenter = presenter;
	}

	/**
	 * Construct a new ButtonCell that will use a given {@link SafeHtmlRenderer}
	 * .
	 * 
	 * @param renderer
	 *            a {@link SafeHtmlRenderer SafeHtmlRenderer<String>} instance
	 */
	public CelluleCliquable(SafeHtmlRenderer<String> renderer) {
		super(renderer, "click", "keydown");
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
			ValueUpdater<String> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if ("click".equals(event.getType())) {
			presenter.changerEcran(value);
		}
	}

	@Override
	public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<div>");
		if (data != null) {
			sb.append(data);
		}
		sb.appendHtmlConstant("</div>");
	}

	@Override
	protected void onEnterKeyDown(Context context, Element parent, String value, NativeEvent event,
			ValueUpdater<String> valueUpdater) {
		if (valueUpdater != null) {
			valueUpdater.update(value);
		}
	}
}
