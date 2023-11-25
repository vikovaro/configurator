import { IConfiguration, IMotherboard, IPowerunit, IProcessor, IVideocard } from "../shared/types/dataTypes";
export class U {
    public static dataTransform(data: any, type: string): string {
        // это всё - огромнейший костыль
        let out = ""
        if (type == "config") {
            out = 'Процессор: ' + data.processor + "\nВидеокарта: " + data.videocard +
                "\nМатеринская плата: " + data.motherboard + "\nОперативная память: " +
                data.ram + " гб\nПостоянная память: " + data.rom + " гб\nБлок питания: " +
                data.powerunit + "\nЦена: " + data.price+"$";
        }
        if (type == "cpu") {
            try {
                const parsedData: IProcessor[] = JSON.parse(JSON.stringify(data));
                for (const processor of parsedData) {
                    out += "Название: " + processor.name + ";  Сокет: " + processor.socket + ";   Цена: " + processor.price + "$\n";
                }
            } catch (e: any) {
                console.log(e.message)
            }
        }
        if (type == "gpu") {
            try {
                const parsedData: IVideocard[] = JSON.parse(JSON.stringify(data));
                for (const videocard of parsedData) {
                    out += "Название: " + videocard.name + ";  PCI: " + videocard.pci + ";   Цена: " + videocard.price + "$\n";
                }
            } catch (e: any) {
                console.log(e.message)
            }
        }
        if (type == "powerunit") {
            try {
                const parsedData: IPowerunit[] = JSON.parse(JSON.stringify(data));
                for (const powerunit of parsedData) {
                    out += "Название: " + powerunit.name + ";  Мощность: " + powerunit.wattage + " Ватт;   Цена: " + powerunit.price + "$\n";
                }
            } catch (e: any) {
                console.log(e.message)
            }
        }
        if (type == "motherboard") {
            try {
                const parsedData: IMotherboard[] = JSON.parse(JSON.stringify(data));
                for (const motherboard of parsedData) {
                    out += "Название: " + motherboard.name + ";  Сокет: " + motherboard.socket + ";   Цена: " + motherboard.price + "$\n";
                }
            } catch (e: any) {
                console.log(e.message)
            }
        }

        return out;
    }
}

