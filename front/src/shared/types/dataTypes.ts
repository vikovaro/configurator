export interface IProcessor {
    name: string;
    socket: string;
    price: number;
}
  
export interface IVideocard {
    name: string;
    pci: string;
    price: number;
}
  
export interface IMotherboard {
    name: string;
    socket: string;
    price: number;
}
  
export interface IPowerunit {
    name: string;
    wattage: string;
    price: number;
}
  
export interface IConfiguration {
    processor: string;
    videocard: string;
    motherboard: string;
    ram: string;
    rom: string;
    powerunit: string;
    price: number;
}