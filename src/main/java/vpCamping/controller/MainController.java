/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpCamping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vpCamping.object.SaleParameters;
import vpCamping.object.TimeParameters;
import vpCamping.object.UserParameters;
import vpCamping.process.MainProcess;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author LRC
 */
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String SALE_ID = "SALE_ID";
    private static final String SUB_MENUS = "SUB_MENUS";
    private static final String SIZE = "SIZE";
    private static final String MARKS = "MARKS";
    private static final String START_MONTH = "START_MONTH";
    private static final String START_DAY = "START_DAY";
    private static final String START_HOUR = "START_HOUR";
    private static final String START_MINUIT = "START_MINUIT";
    private static final String SHUT_DOWN_HOUR = "SHUT_DOWN_HOUR";
    private static final String SHUT_DOWN_MIN = "SHUT_DOWN_MIN";
    private static final String SHUT_DOWN_ACTIVE = "SHUT_DOWN_ACTIVE";

    public static void start(String configFile) {

        boolean success = true;
        UserParameters userParameters = null;
        SaleParameters saleParameters = null;
        TimeParameters timeParam = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(MainController.class.getClassLoader().getResource(configFile).getPath());
            Properties properties = new Properties();
            properties.load(fileInputStream);
            userParameters = getUserPara(properties);
            saleParameters = getSaleParameters(properties);
            timeParam = getTimeParameters(properties);


        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            success = false;
        } finally {
            if (success) {
                MainProcess businessLogic = new MainProcess(saleParameters, userParameters, timeParam);
                businessLogic.start();
            }
        }
    }

    private static TimeParameters getTimeParameters(Properties properties) {
        //init time parameter
        TimeParameters.Builder timeParaBuilder = new TimeParameters.Builder();
        timeParaBuilder.withStartMonth(Integer.valueOf(properties.getProperty(START_MONTH)));
        timeParaBuilder.withStartDay(Integer.valueOf(properties.getProperty(START_DAY)));
        timeParaBuilder.withStartHour(Integer.valueOf(properties.getProperty(START_HOUR)));
        timeParaBuilder.withStartMinute(Integer.valueOf(properties.getProperty(START_MINUIT)));

        String shutdownActivation = properties.getProperty(SHUT_DOWN_ACTIVE);
        if (shutdownActivation != null && Boolean.valueOf(shutdownActivation).equals(true)) {
            timeParaBuilder.withShutDownHour(Integer.valueOf(properties.getProperty(SHUT_DOWN_HOUR)));
            timeParaBuilder.withShutDownMinute(Integer.valueOf(properties.getProperty(SHUT_DOWN_MIN)));
        }
        int refreshTimeInt = 3;
        return timeParaBuilder.withRefreshTime(refreshTimeInt).build();
    }

    private static SaleParameters getSaleParameters(Properties properties) {
        //init sale parameter
        SaleParameters.Builder saleParametersBuilder = new SaleParameters.Builder();
        String text = properties.getProperty(SALE_ID);
        saleParametersBuilder.withSaleIdValue(text);
        text = properties.getProperty(MARKS);
        saleParametersBuilder.withExpectedMarkValue(text);

        text = properties.getProperty(SUB_MENUS);
        saleParametersBuilder.withExpectedSubMenuList(Arrays.asList(text.toUpperCase().split(",")));

        text = properties.getProperty(SIZE);
        saleParametersBuilder.withExpecedSizeList(Arrays.asList(text.toUpperCase().split(",")));

        saleParametersBuilder.withScrollPageFrequence(4);
        return saleParametersBuilder.build();
    }

    private static UserParameters getUserPara(Properties properties) {
        //init user parameter
        String login = properties.getProperty(LOGIN);
        String pwd = properties.getProperty(PASSWORD);
        return new UserParameters.Builder(login, pwd).build();
    }
}
