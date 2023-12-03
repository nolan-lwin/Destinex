import React, {useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import './add_bank_acc.css';
import 'react-phone-number-input/style.css';
import {granterRegister} from "../../../../networks/hooks/UseGranterRegister";
import {getUsername} from "../../../../networks/utils/AuthUtils";

function AddBankAcc () {
    const navigate = useNavigate();
    const handleRegisterGranter = async (event) => {
        event.preventDefault();

        const currentLocation = {
            "type": "Point",
            "coordinates": JSON.parse(sessionStorage.getItem("granterAddressPoint"))
        }
        const email = getUsername();
        const vehicleDetails = sessionStorage.getItem("vehicleDetails")
        const nationalIdNumber = sessionStorage.getItem("nationalIdNumber")
        const driverLicense = sessionStorage.getItem("driverLicense")

        console.log("----------")
        console.log(currentLocation, email, vehicleDetails, nationalIdNumber, driverLicense);

        const successGranterRegister = await granterRegister(currentLocation, email, vehicleDetails, nationalIdNumber, driverLicense)
        if (successGranterRegister) {
            navigate("/granter/congrats");
        }
        else {
            console.log("Granter Register failed");
        }
    }

    return(
        <form onSubmit={handleRegisterGranter}>
            <div className='bankPage'>

                <div className='addBankAcc'>
                    <span className='addBankAccountTitle'>Add Your Bank Account</span>
                </div>

                <div className='personalDetails'>
                    <span className='personalDetailsTitle'>Personal Details</span>
                </div>

                <div className='personalDetailsForm'>
                    <div className='nameContainer'>
                        <div className='firstName'>
                            <label className='firstNameText'>First Name</label>
                            <input type='text' placeholder='Enter your first name'/>
                        </div>

                        <div className='lastName'>
                            <label className='lastNameText'>Last Name</label>
                            <input type='text' placeholder='Enter your last name'/>
                        </div>
                    </div>
                </div>

                <div className='bankInfo'>
                    <span className='bankInfoTitle'>Bank Information</span>
                </div>

                <div className='bankInfoForm'>
                    <div className='bankNameAndAccountTypeContainer'>
                        <div className='bankName'>
                            <label className='bankNameText'>Bank Name</label>
                            <input type='text' placeholder='Enter your bank name'/>
                        </div>

                        <div className='accountType'>
                            <label className='accountTypeText'>Account Type</label>
                            <select className='accountTypeDropdown'>
                                <option value="selectAccountType">Select Account Type</option>
                                <option value="checking">Checking</option>
                                <option value="savings">Savings</option>
                            </select>
                        </div>
                    </div>

                    <div className='accountNumberContainer'>
                        <div className='accountNumber'>
                            <label className='accountNumberText'>Account Number</label>
                            <input type='text' placeholder='Enter your account number'/>
                        </div>

                        <div className='confirmAccountNumber'>
                            <label className='confirmAccountNumberText'>Confirm Account Number</label>
                            <input type='text' placeholder='Confirm your account number'/>
                        </div>
                    </div>

                    <div className='routingNumberContainer'>
                        <div className='routingNumber'>
                            <label className='routingNumberText'>Routing Number</label>
                            <input type='text' placeholder='Enter your routing number'/>
                        </div>
                    </div>
                </div>

                <div className="submitGranterButton">
                    <button type={'submit'} className='submitGranterBtn'>
                        Link Bank Account
                    </button>
                </div>
            </div>
        </form>
    )
}

export default AddBankAcc;