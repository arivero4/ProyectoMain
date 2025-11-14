package ui.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Filtros de documento para validación de entrada en campos de texto.
 */
public final class DocumentFilters {

	/**
	 * Constructor privado para evitar instanciación.
	 */
	private DocumentFilters() {
		throw new AssertionError("No se puede instanciar la clase DocumentFilters");
	}

	/**
	 * Filtro para permitir solo números enteros.
	 */
	public static class IntegerFilter extends DocumentFilter {
		private int maxLength;

		public IntegerFilter() {
			this.maxLength = Integer.MAX_VALUE;
		}

		public IntegerFilter(int maxLength) {
			this.maxLength = maxLength;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string == null) {
				return;
			}

			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

			if (isValid(newText) && newText.length() <= maxLength) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}

			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + text
					+ currentText.substring(offset + length);

			if (isValid(newText) && newText.length() <= maxLength) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		private boolean isValid(String text) {
			if (text.isEmpty()) {
				return true;
			}
			try {
				Integer.parseInt(text);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/**
	 * Filtro para permitir solo números decimales.
	 */
	public static class DecimalFilter extends DocumentFilter {
		private int maxLength;

		public DecimalFilter() {
			this.maxLength = 20;
		}

		public DecimalFilter(int maxLength) {
			this.maxLength = maxLength;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string == null) {
				return;
			}

			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

			if (isValid(newText) && newText.length() <= maxLength) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}

			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + text
					+ currentText.substring(offset + length);

			if (isValid(newText) && newText.length() <= maxLength) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		private boolean isValid(String text) {
			if (text.isEmpty()) {
				return true;
			}
			try {
				Double.parseDouble(text);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	/**
	 * Filtro para limitar longitud máxima de texto.
	 */
	public static class LengthFilter extends DocumentFilter {
		private int maxLength;

		public LengthFilter(int maxLength) {
			this.maxLength = maxLength;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string == null) {
				return;
			}

			if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}

			if ((fb.getDocument().getLength() + text.length() - length) <= maxLength) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}

	/**
	 * Filtro para permitir solo letras y espacios.
	 */
	public static class AlphaFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string == null) {
				return;
			}

			if (string.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text == null) {
				super.replace(fb, offset, length, text, attrs);
				return;
			}

			if (text.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}
}
