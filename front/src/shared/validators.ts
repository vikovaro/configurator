export class V {
    public static onlyNumbers(value: any) {
        if (Number.isNaN(+value)) {
            return false;
        }
        return true;
    }
}