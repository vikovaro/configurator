import axios, { AxiosError, AxiosInstance, AxiosRequestConfig } from "axios";

export class Backend {
    constructor(config: AxiosRequestConfig) {
        this.axios = axios.create(config);
    }

    protected async request(config: Partial<AxiosRequestConfig>) {
        try {
            return await this.axios.request(config);
        } catch (error: any) {
            throw error;
        }
    }

    protected axios: AxiosInstance;
}
