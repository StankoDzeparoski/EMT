import { Box, Container, Typography } from '@mui/material';

const Footer = () => {
  return (
    <Box
      component="footer"
      sx={{
        backgroundColor: '#f5f5f5',
        padding: 3,
        marginTop: 4,
        borderTop: '1px solid #ddd'
      }}
    >
      <Container maxWidth="lg">
        <Typography variant="body2" color="textSecondary" align="center">
          © 2026 Travel Accommodations. All rights reserved.
        </Typography>
      </Container>
    </Box>
  );
};

export default Footer;
