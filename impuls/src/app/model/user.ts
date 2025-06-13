export interface User {
email: string;
  password: string;
  isVerified?: boolean;
  isActive?: boolean;
  role: {
    id: number;
  };
  profileRequest: ProfileRequest;
}

export interface ProfileRequest {
  firstName: string;
  lastName: string;
  phone: string;
  avatarUrl?: string;
  socialNetworkRequests?: SocialNetworkRequest[];
  addressRequests?: AddressRequest[];
}

export interface SocialNetworkRequest {
  name: string;
  url: string;
}

export interface AddressRequest {
  street: string;
  neighborhood: string;
  zipCode: string;
  isPrimary: boolean;
  city: string | null;
}