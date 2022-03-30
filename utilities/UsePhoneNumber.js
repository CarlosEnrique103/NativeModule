import {NativeModules} from 'react-native';

const {PhoneModule} = NativeModules;

export const GetPhoneNumber = async () => await PhoneModule.getPhone();
