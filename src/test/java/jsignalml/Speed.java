package jsignalml;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class Speed {
	public interface Reader {
		long checksum() throws java.io.IOException;
		String title();
	}

	String name;
	int SIZE;
	int BIGSIZE;

	public static int char2int(byte c){
		return c < 0 ? c + 256 : c;
	}

	public class Reader1 implements Reader {
		public long checksum()  throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			int b;
			long checkSum = 0L;
			while ( (b=f.read()) != -1 )
				checkSum += b;
			return checkSum;
		}
		public String title() {
			return "FileInputStream";
		}
	}


	public class Reader2 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nRead;
			while ( (nRead=f.read( barray, 0, SIZE )) != -1 )
				for ( int i=0; i<nRead; i++ )
					checkSum += char2int(barray[i]);
			return checkSum;
		}
		public String title() {
			return "FileInputStream/" + SIZE;
		}
	}


	public class Reader3 implements Reader {
		public long checksum() throws java.io.IOException {
			BufferedInputStream f = new BufferedInputStream(
				new FileInputStream( name ) );
			int b;
			long checkSum = 0L;
			while ( (b=f.read()) != -1 )
				checkSum += b;
			return checkSum;
		}
		public String title() {
			return "BufferedInputStream";
		}
	}


	public class Reader4 implements Reader {
		public long checksum() throws java.io.IOException {
			BufferedInputStream f = new BufferedInputStream(
				new FileInputStream( name ) );
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nRead;
			while ( (nRead=f.read( barray, 0, SIZE )) != -1 )
				for ( int i=0; i<nRead; i++ )
					checkSum += char2int(barray[i]);
			return checkSum;
		}
		public String title() {
			return "BufferedInputStream/" + SIZE;
		}
	}

	public class Reader5 implements Reader {
		public long checksum() throws java.io.IOException {
			RandomAccessFile f = new RandomAccessFile( name, "r" );
			int b;
			long checkSum = 0L;
			while ( (b=f.read()) != -1 )
				checkSum += b;
			return checkSum;
		}
		public String title() {
			return "RandomAccessFile";
		}
	}

	public class Reader6 implements Reader {
		public long checksum() throws java.io.IOException {
			RandomAccessFile f = new RandomAccessFile( name, "r");
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nRead;
			while ( (nRead=f.read( barray, 0, SIZE )) != -1 )
				for ( int i=0; i<nRead; i++ )
					checkSum += char2int(barray[i]);
			return checkSum;
		}
		public String title() {
			return "RandomAccessFile/" + SIZE;
		}
	}

	public class Reader7 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocate( BIGSIZE );
			long checkSum = 0L;
			int nRead;
			while ( (nRead=ch.read( bb )) != -1 ) {
				if ( nRead == 0 )
					continue;
				bb.position( 0 );
				bb.limit( nRead );
				while ( bb.hasRemaining() )
					checkSum += char2int(bb.get( ));
				bb.clear( );
			}
			return checkSum;
		}
		public String title() {
			return "ByteBuffer.allocate/" + BIGSIZE;
		}
	}

	public class Reader8 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocate( BIGSIZE );
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nRead, nGet;
			while ( (nRead=ch.read( bb )) != -1 ) {
				if ( nRead == 0 )
					continue;
				bb.position( 0 );
				bb.limit( nRead );
				while( bb.hasRemaining( ) )	{
					nGet = Math.min( bb.remaining( ), SIZE );
					bb.get( barray, 0, nGet );
					for ( int i=0; i<nGet; i++ )
						checkSum += char2int(barray[i]);
				}
				bb.clear( );
			}
			return checkSum;
		}
		public String title() {
			return "ByteBuffer.allocate/" + BIGSIZE + "/" + SIZE;
		}
	}

	public class Reader9 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			byte[] barray = new byte[SIZE];
			ByteBuffer bb = ByteBuffer.wrap( barray );
			long checkSum = 0L;
			int nRead;
			while ( (nRead=ch.read( bb )) != -1 ) {
				for ( int i=0; i<nRead; i++ )
					checkSum += char2int(barray[i]);
				bb.clear( );
			}
			return checkSum;
		}
		public String title() {
			return "ByteBuffer.wrap/" + SIZE;
		}
	}

	public class Reader10 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocateDirect( SIZE );
			long checkSum = 0L;
			int nRead;
			while ( (nRead=ch.read( bb )) != -1 ) {
				bb.position( 0 );
				bb.limit( nRead );
				while ( bb.hasRemaining() )
					checkSum += char2int(bb.get( ));
				bb.clear( );
			}
			return checkSum;
		}
		public String title() {
			return "ByteBuffer.allocateDirect";
		}
	}

	public class Reader11 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocateDirect( BIGSIZE );
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nRead, nGet;
			while ( (nRead=ch.read( bb )) != -1 ) {
				if ( nRead == 0 )
					continue;
				bb.position( 0 );
				bb.limit( nRead );
				while( bb.hasRemaining( ) ) {
					nGet = Math.min( bb.remaining( ), SIZE );
					bb.get( barray, 0, nGet );
					for ( int i=0; i<nGet; i++ )
						checkSum += char2int(barray[i]);
				}
				bb.clear( );
			}
			return checkSum;
		}
		public String title() {
			return "ByteBuffer.allocateDirect/" + SIZE;
		}
	}

	public class Reader12 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			long checkSum = 0L;
			long size = ch.size();
			for(long i=0; i<size; i++)
				checkSum += char2int(mb.get( ));
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer";
		}
	}

	public class Reader12a implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			long checkSum = 0L;
			long size = ch.size();
			for(long i=0; i<size; i++)
				checkSum += char2int(mb.get((int)i));
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer absolute";
		}
	}

	public class Reader12b implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			mb.order(ByteOrder.LITTLE_ENDIAN);

			long checkSum = 0L;
			long size = ch.size();
			for(long i=0; i<size; i++)
				checkSum += char2int(mb.get((int)i));
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer absolute LE";
		}
	}

	public class Reader12c implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			ByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			mb.order(ByteOrder.BIG_ENDIAN);

			long checkSum = 0L;
			long size = ch.size();
			for(long i=0; i<size; i++)
				checkSum += char2int(mb.get((int)i));
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer absolute BE";
		}
	}

	public class Reader12d implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			long checkSum = 0L;
			long size = ch.size();
			for(long i=size-1; i>=0; i--)
				checkSum += char2int(mb.get((int)i));
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer reverse";
		}
	}

	public class Reader13 implements Reader {
		public long checksum() throws java.io.IOException {
			FileInputStream f = new FileInputStream( name );
			FileChannel ch = f.getChannel( );
			MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
						      0L, ch.size( ) );
			byte[] barray = new byte[SIZE];
			long checkSum = 0L;
			int nGet;
			while( mb.hasRemaining( ) ) {
				nGet = Math.min( mb.remaining( ), SIZE );
				mb.get( barray, 0, nGet );
				for ( int i=0; i<nGet; i++ )
					checkSum += char2int(barray[i]);
			}
			return checkSum;
		}
		public String title() {
			return "MappedByteBuffer/" + SIZE;
		}
	}




	public Speed(String name, int SIZE) {
		this.name = name;
		this.SIZE = this.BIGSIZE = SIZE;
	}

	public void printSpeeds() throws java.io.IOException {
		Reader readers[] = {new Reader1(),
				    new Reader2(),
				    new Reader3(),
				    new Reader4(),
				    new Reader5(),
				    new Reader6(),
				    new Reader7(),
				    new Reader8(),
				    new Reader9(),
				    new Reader10(),
				    new Reader11(),
				    new Reader12(),
				    new Reader12a(),
				    new Reader12b(),
				    new Reader12c(),
				    new Reader12d(),
				    new Reader13(),
				    };
		for(Reader reader:readers){
			long start = System.currentTimeMillis();
			long checkSum;
			int count=0;
			long stop;
			while(true){
				checkSum = reader.checksum();
				count++;
				stop = System.currentTimeMillis();
				if(stop-start > 100)
					break;
			}
			System.out.println(String.format("%-30s checksum=%d time=%f",
							 reader.title(), checkSum,
							 ((double)(stop-start))/count));
		}
	}

	public static void main(String...argv) throws java.io.IOException
	{
		new Speed(argv[0], 1024*16).printSpeeds();
	}
}
