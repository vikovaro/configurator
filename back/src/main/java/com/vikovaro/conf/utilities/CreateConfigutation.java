package com.vikovaro.conf.utilities;

import com.vikovaro.conf.DTO.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

import static com.vikovaro.conf.utilities.DatabaseConnection.*;

public class CreateConfigutation {
    public static ConfigurationResponse getConfigutation(int price) {
        int sum=0;
        int rang;
        String ram ="";
        String rom = "";
        if (price<501) {
            rang = 1;
        } else if (price<1500) {
            rang = 2;
        } else {
            rang = 3;
        }

        switch (rang) {
            case 1 -> {
                ram = "8";
                rom = "256";
                sum+=100;
            }
            case 2 -> {
                ram = "16";
                rom = "512";
                sum+=200;
            }
            case 3 -> {
                ram = "32";
                rom = "1024";
                sum+=300;
            }
        }
        Random rnd = new Random();

        ArrayList<Processor> cpus = getCPUForConf(rang);

        Processor cpu;
        cpu = cpus.get(rnd.nextInt(cpus.size()));
        sum+=cpu.getPrice();

        ArrayList<Motherboard> motherboards = getMotherBoardForConf(cpu.getSocket());
        ArrayList<Videocard> gpus = getGPUForConf(rang);
        ArrayList<Powerunit> powerunits = getPowerUnitForConf(rang);

        String mb = "";
        String gpu = "";
        String powerunit = "";
        String wattage = "";

        int abs = 500;
        int outSum = 0;
        for (int i = 0; i < 20; i++) {
            int tempSum=sum;
            Motherboard m = motherboards.get(rnd.nextInt(motherboards.size()));
            Videocard g = gpus.get(rnd.nextInt(gpus.size()));
            Powerunit p = powerunits.get(rnd.nextInt(powerunits.size()));
            tempSum=tempSum+m.getPrice()+g.getPrice()+p.getPrice();

            if (Math.abs(price-tempSum) < abs) {
                abs = Math.abs(price-tempSum);
                mb = m.getName();
                gpu = g.getName();
                powerunit = p.getName();
                wattage = p.getWattage();
                outSum = tempSum;
            }
        }


        if (abs>100) {
            outSum = -1;
        }





        return new ConfigurationResponse(cpu.getName(), gpu, mb, ram, rom, powerunit+" "+wattage+"Вт", outSum);


    }


    private static ArrayList<Powerunit> getPowerUnitForConf(int rang) {
        ArrayList<Powerunit> list = getPowerunits();

        ArrayList<Powerunit> pw = new ArrayList<>();
        for (Powerunit powerunit : list) {
            if (rang == powerunit.getRang()) {
                pw.add(powerunit);
            }
        }
        return pw;
    }

    private static ArrayList<Processor> getCPUForConf(int rang) {
        ArrayList<Processor> list = getProcessors();

        ArrayList<Processor> cpus = new ArrayList<>();
        for (Processor cpu : list) {
            if (rang == cpu.getRang()) {
                cpus.add(cpu);
            }
        }
        return cpus;
    }

    private static ArrayList<Motherboard> getMotherBoardForConf(String socket) {
        ArrayList<Motherboard> list = getMotherboards();

        ArrayList<Motherboard> mbs = new ArrayList<>();
        for (Motherboard motherboard : list) {
            if (socket.equals(motherboard.getSocket())) {
                mbs.add(motherboard);
            }
        }

        return mbs;
    }

    private static ArrayList<Videocard> getGPUForConf(int rang) {
        ArrayList<Videocard> list = getVideocards();

        ArrayList<Videocard> gpus = new ArrayList<>();
        for (Videocard videocard : list) {
            if (rang==videocard.getRang()) {
                gpus.add(videocard);
            }
        }
        return gpus;
    }




}
