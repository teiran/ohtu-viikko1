package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(-3, -3);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(-3);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        varasto = new Varasto(10, -3);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(10, 7);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(7, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(10, 11);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(-3);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(4);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
        saatuMaara = varasto.otaVarastosta(8);
        assertEquals(6, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(10, 11);
        saatuMaara = varasto.otaVarastosta(-5);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        varasto = new Varasto(10);
        String k = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        double g = 0.0;
        if (k.equals(varasto.toString()) ) {
            g = 1.0;
        }
        assertEquals(1.0, g, vertailuTarkkuus);
    }
}
