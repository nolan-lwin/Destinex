import * as addWishService from "../api/AddWishService"

export const addJob = async(productName, endDate, itemPrice, category, receiverName, receiverPhone, receiverEmail, receiverAddress, description, receiverAddressPoint) => {
    try {
        const jobData = {
            "productName": productName,
            "endDate": endDate,
            "itemPrice": itemPrice,
            "category": category,
            "receiverName": receiverName,
            "receiverPhone": receiverPhone,
            "receiverEmail": receiverEmail,
            "receiverAddress": receiverAddress,
            "description": description,
            "receiverAddressPoint": receiverAddressPoint
        };

        console.log(jobData)
        const response = await addWishService.addJob(jobData);
        console.log("Upload a job successfully: ", response.data);
        if (response.data && response.data.id) {
            return response.data.id;
        } else {
            throw new Error("Job ID not received in the response");
        }
    } catch (error) {
        console.error(error);
        throw error;
    }
}
