import {
  Card,
  CardContent,
  CardHeader,
  Typography
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import type { DisplayCountryDto } from '../../../api/types/Country';

interface CountryCardProps {
  country: DisplayCountryDto;
}

const CountryCard = ({ country }: CountryCardProps) => {
  return (
    <RouterLink to={`/countries/${country.id}`} style={{ textDecoration: 'none' }}>
      <Card
        sx={{
          height: '100%',
          display: 'flex',
          flexDirection: 'column',
          cursor: 'pointer',
          transition: 'all 0.3s ease',
          '&:hover': {
            boxShadow: '0 8px 24px rgba(0, 0, 0, 0.15)',
            transform: 'translateY(-4px)'
          }
        }}
      >
        <CardHeader
          title={country.name}
          titleTypographyProps={{ variant: 'h6' }}
        />
        <CardContent sx={{ flexGrow: 1 }}>
          <Typography color="textSecondary" gutterBottom>
            <strong>ID:</strong> {country.id}
          </Typography>

          <Typography color="textSecondary" gutterBottom>
            <strong>Name:</strong> {country.name}
          </Typography>

          <Typography color="textSecondary" gutterBottom>
            <strong>Continent:</strong> {country.continent}
          </Typography>
        </CardContent>
      </Card>
    </RouterLink>
  );
};

export default CountryCard;
