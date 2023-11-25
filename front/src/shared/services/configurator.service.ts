import axios, { AxiosError, AxiosInstance, AxiosRequestConfig } from "axios";
import { Backend } from "./backend.service";
import { IConfiguration, IMotherboard, IPowerunit, IProcessor, IVideocard } from "../types/dataTypes";
import { ErrorResponse } from "react-router-dom";

type TSendComponentData = {
    type: string
    name: string
    rang: number
    spec: string
    price: number
};

class Configurator extends Backend {
    constructor() {
        super({
            baseURL: 'http://localhost:8090',
        });
    }


    public async getConfiguration(price: number) {
        try {
            const response: IConfiguration = (await this.request({
                url: '/getConfiguration',
                headers: {
                    price
                },
            })).data;
    
            return response;
        } catch(error: any) {
            throw error;
        }

    }

    public async getCpus() {
        const response: Array<IProcessor> = (await this.request({
            url: '/getProcessors',
        })).data;

        return response;
    }

    public async getMotherboards() {
        const response: Array<IMotherboard> = (await this.request({
            url: '/getMotherboards',
        })).data;

        return response;
    }

    public async getGpus() {
        const response: Array<IVideocard> = (await this.request({
            url: '/getVideocards',
        })).data;

        return response;
    }

    public async getPowerunits() {
        const response: Array<IPowerunit> = (await this.request({
            url: '/getPowerunits',
        })).data;

        return response;
    }

    public async sendComponent(data: TSendComponentData) {
        const response = await this.request({
            method: 'post', // Specify the HTTP method as POST
            url: '/addComponent',
            data,
        });

        return response.status;
    }
}

export default new Configurator();