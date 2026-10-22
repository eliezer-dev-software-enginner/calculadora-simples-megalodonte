package megalodonte.app.calculadora_simples;

import megalodonte.base.components.Component;
import megalodonte.base.components.ScreenComponent;
import megalodonte.base.state.State;
import megalodonte.components.Button;
import megalodonte.components.Text;
import megalodonte.components.Scroll;
import megalodonte.components.inputs.Input;
import megalodonte.components.layout_components.Column;
import megalodonte.props.ColumnProps;
import megalodonte.props.InputProps;
import megalodonte.props.TextProps;
import java.math.BigDecimal;
import java.math.MathContext;

public final class HomeScreen implements ScreenComponent {
    private final State<String> first = State.of("");
    private final State<String> second = State.of("");
    private final State<String> result = State.of("Informe dois números.");

    /** Keeps the displayed result current by clearing it whenever an operand changes. */
    public HomeScreen() {
        first.subscribe(value -> result.set("Informe dois números."));
        second.subscribe(value -> result.set("Informe dois números."));
    }

    @Override public Component render() {
        return new Scroll(new Column(new ColumnProps().paddingAll(24).spacingOf(14).fillWidth()).children(
                new Text("Calculadora", new TextProps().fontSize(28)),
                new Text("Operações básicas com números decimais."),
                new Input(first, new InputProps().placeHolder("Primeiro número")),
                new Input(second, new InputProps().placeHolder("Segundo número")),
                new Button("Somar").onClick(() -> calculate('+')),
                new Button("Subtrair").onClick(() -> calculate('-')),
                new Button("Multiplicar").onClick(() -> calculate('*')),
                new Button("Dividir").onClick(() -> calculate('/')),
                new Button("Limpar").onClick(() -> { first.set(""); second.set(""); result.set("Informe dois números."); }),
                new Text(result, new TextProps().fontSize(22))));
    }

    private void calculate(char operation) {
        try {
            BigDecimal a = new BigDecimal(first.get().trim().replace(',', '.'));
            BigDecimal b = new BigDecimal(second.get().trim().replace(',', '.'));
            BigDecimal value = switch (operation) {
                case '+' -> a.add(b);
                case '-' -> a.subtract(b);
                case '*' -> a.multiply(b);
                case '/' -> a.divide(b, MathContext.DECIMAL64);
                default -> throw new IllegalArgumentException("Operação desconhecida");
            };
            result.set("Resultado: " + value.stripTrailingZeros().toPlainString());
        } catch (ArithmeticException error) {
            result.set("Não é possível dividir por zero.");
        } catch (NumberFormatException error) {
            result.set("Informe dois números válidos.");
        }
    }
}
