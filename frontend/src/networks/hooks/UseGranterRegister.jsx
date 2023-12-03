import * as granterRegisterService from "../api/GranterRegisterService"

export const granterRegister = async(currentLocation, email, vehicleDetails, nationalIdNumber, driverLicense) => {
    try {
        const response = await granterRegisterService.granterRegister({currentLocation, email, vehicleDetails, nationalIdNumber, driverLicense})
        if (response.data) {
            return true;
        } else {
            return false;
        }
    } catch(err) {
        console.log(err.message)
        return false;
    }
}