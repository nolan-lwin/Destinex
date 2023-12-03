import React, { useState } from 'react';
import Select from 'react-select';
import { Link } from "react-router-dom";
import { CountryDropdown, RegionDropdown } from 'react-country-region-selector';
import './setup_form.css';

function SetupPage () {
    const [vehicleDetails, setVehicleDetails] = useState('');
    const [nationalIdNumber, setNationalIdNumber] = useState('');
    const [driverLicense, setDriverLicense] = useState('');

    const handleVehicleChange = (event) => {
        const newVehicle = event.target.value
        setVehicleDetails(newVehicle)
        sessionStorage.setItem("vehicleDetails", newVehicle)
    }

    const handleIDChange = (event) => {
        const newId = event.target.value
        setNationalIdNumber(newId)
        sessionStorage.setItem("nationalIdNumber", newId)
    }

    const handleDriverLicenseChange = (event) => {
        const newLicense = event.target.value
        setDriverLicense(newLicense)
        sessionStorage.setItem("driverLicense", newLicense)
    }

    return (
        <div className='setupForm'>

            <div className='vehicleDetails'>
                <span className='vehicleDetailsTitle'>Vehicle Details</span>
            </div>

            <div className='setupFormInput'>

                <div className='vehicleType'>
                    <label className='vehicleTypeText'>Vehicle Type</label>
                    <select className='vehicleTypeDropdown'>
                        <option value="selectVehicle">Select Vehicle</option>
                        <option value="bicycle">Bicycle</option>
                        <option value="scooter">Scooter</option>
                        <option value="motorcycle">Motorcycle</option>
                        <option value="car">Car</option>
                        <option value="van">Van</option>
                        <option value="truck">Truck</option>
                        <option value="cargoBike">Cargo Bike</option>
                        <option value="electricBike">Electric Bike (eBike)</option>
                        <option value="electricScooter">Electric Scooter (eScooter)</option>
                        <option value="walk">Walk</option>
                        <option value="publicTransportation">Public Transportation</option>
                    </select>
                </div>

                <div className='vehicleModel'>
                    <label className='vehicleModelText'>Vehicle Model</label>
                    <input type='text' placeholder='Enter your vehicle model'
                        onChange={handleVehicleChange}
                    />
                </div>

            </div>

            <div className='backgroundCheck'>
                <span className='backgroundCheckTitle'>Background Check</span>
            </div>

            <div className='setupFormInput'>

                <div className='sSN'>
                    <label className='sSNText'>Social Security Number</label>
                    <input type='text' placeholder='Enter your SSN'
                        onChange={handleIDChange}
                    />
                </div>

                <div className='driversLicenseNumber'>
                    <label className='driversLicenseNumberText'>Driver's License Number</label>
                    <input type='text' placeholder='Enter your driver&apos;s license number'
                        onChange={handleDriverLicenseChange}
                    />
                </div>

                <div className='dob'>
                    <label className='dobText'>Date of Birth</label>
                    <input type='text' placeholder='Enter your date of birth'/>
                </div>

                <div className='attachFile'>
                    <label className='attachFileText'>Attach File</label>
                    <input type='file' placeholder='Attach file'/>
                </div>

                <div className='agreement'>
                    <input type='checkbox'/>
                    <span className='agreementText'>
                        I acknowledge that I have read and agree to the documents such as <a href="link-to-terms" className="linkStyle">Background Check Disclosures</a>, <a href="link-to-privacy" className="linkStyle">Additional Disclosures</a>, and <a href="link-to-notifications" className="linkStyle">Authorization</a>. I authorize Destinex to order reports about me.
                    </span>
                </div>

                <Link to="/granter/bank" className='continueButtonText'>
                    Continue
                </Link>

            </div>

        </div>
    )
}

export default SetupPage;