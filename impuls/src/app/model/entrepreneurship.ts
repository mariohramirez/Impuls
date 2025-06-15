export interface EntrepreneurshipResponse {
  ownerId: number;
  name: string;
  email: string;
  phone: string;
  logoUrl: string;
  websiteUrl: string;
  shortDescription: string;
  bannerUrl: string;
  description: string;
  addressResponses?: AddressResponse[];
  socialNetworkResponses?: SocialNetworkResponse[];
  categoryResponses?: CategoryResponse[];
  serviceResponses?: ServiceResponse[];
}

export interface AddressResponse {
  street: string;
  neighborhood: string;
  zipCode: string;
  city: City;
}

export interface City {
  id: number;
  name: string;
  // Agrega más campos si tu backend los incluye
}

export interface SocialNetworkResponse {
  name: 'facebook' | 'twitter' | 'instagram' | 'linkedin'; // Restringimos a estas redes
  url: string;
}

export interface CategoryResponse {
  name: string;
  description: string;
  icon: string;
}

export interface ServiceResponse {
  name: string;
  description: string;
}