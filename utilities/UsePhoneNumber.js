import {NativeModules} from 'react-native';

const {PhoneModule} = NativeModules;

export const GetPhoneNumber = () =>
  PhoneModule.getPhone().then(number => console.log({number}));
