package com.sda.fastproject.model;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Random;


@Route("")
public class MainGUI extends VerticalLayout {

    @Autowired
    public MainGUI(CurrencyClient currencyClient) {


        CurrencyModel currencyRates = currencyClient.getCurrencyRates();

        int random = new Random().nextInt(4);

        Double value;
        String currency;

        switch (random) {
            case 0:
                value = currencyRates.getRates().getAUD();
                currency = "AUD";
                break;
            case 1:
                value = currencyRates.getRates().getBGN();
                currency = "BNG";
                break;
            case 2:
                value=currencyRates.getRates().getCAD();
                currency = "CAD";
                break;
            case 3:
                value=currencyRates.getRates().getCHF();
                currency= "CHF";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + random);
        }


        DoubleRounder doubleRounder = new DoubleRounder(2);

        NumberField answerField = new NumberField("PLN to: " + currency);
        answerField.setPlaceholder("ile?");

        Button button = new Button(" Zgaduje", new Icon(VaadinIcon.MONEY));

        Label content = new Label(
                "Twoja odpowiedz jest: ");

        Label content2 = new Label();

        button.addClickListener(event ->
                {
                    if(answerField.getValue() > doubleRounder.round(value))
                    {
                        content2.setText("Twoja wartosc jest za duza" );
                    }
                    if(answerField.getValue() < doubleRounder.round(value))
                    {
                        content2.setText("Twoja wartosc jest za mala");
                    }
                    if(answerField.getValue().equals(doubleRounder.round(value)))
                    {
                        content2.setText("Zgadłeś");
                    }

                }
        );

        add(answerField, button, content2);
    }
}
