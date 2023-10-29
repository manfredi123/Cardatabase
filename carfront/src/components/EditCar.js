import React, {useState} from "react";
import { Dialog, Icon } from "@mui/material";
import {DialogActions} from "@mui/material";
import {DialogContent} from "@mui/material";
import {DialogTitle} from "@mui/material";
import {Button} from "@mui/material";
import { IconButton } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit"
import {TextField} from "@mui/material";
import {Stack} from "@mui/material";

function EditCar(props) {
    const [open, setOpen] = useState(false);
    const [car, setCar] = useState({
        brand: '',
        model: '',
        color: '',
        year: '',
        fuel: '',
        price: ''
    });

    const handleClickOpen = () =>{
        setCar({
            brand: props.data.row.brand,
            model: props.data.row.model,
            color: props.data.row.color,
            year: props.data.row.year,
            price: props.data.row.price
        })
        setOpen(true);
    }

    const handleClose = () =>{
        setOpen(false);
    }

    const handleChange = (event) =>{
        setCar({...car, [event.target.name]:
            event.target.value});
    }

    const handleSave = () => {
        props.updateCar(car, props.data.row._links.self.href);
        handleClose();
    }

    return(
        <div>
            <IconButton onClick={handleClickOpen}>
                <EditIcon color="primary" />
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
            <DialogTitle>New Car</DialogTitle>
                <DialogContent>
                    <Stack spacing={2} mt={1}>
                        <TextField label="Brand" name="brand" 
                                autoFocus 
                            variant="standard" value={car.brand} onChange={handleChange}/>
                        <TextField label="Model" name="model" 
                                autoFocus 
                            variant="standard" value={car.model} onChange={handleChange}/>
                        <TextField label="Color" name="color" 
                                autoFocus 
                            variant="standard" value={car.color} onChange={handleChange}/>
                        <TextField label="Year" name="year" 
                                autoFocus 
                            variant="standard" value={car.year} onChange={handleChange}/>
                        <TextField label="Price" name="price" 
                                autoFocus 
                            variant="standard" value={car.price} onChange={handleChange}/>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default EditCar;